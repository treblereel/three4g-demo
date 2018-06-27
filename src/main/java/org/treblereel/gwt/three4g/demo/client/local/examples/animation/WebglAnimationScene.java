package org.treblereel.gwt.three4g.demo.client.local.examples.animation;

import com.google.gwt.animation.client.AnimationScheduler;
import org.treblereel.gwt.three4g.animation.AnimationClip;
import org.treblereel.gwt.three4g.animation.AnimationMixer;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.Clock;
import org.treblereel.gwt.three4g.core.JsObject;
import org.treblereel.gwt.three4g.core.Object3D;
import org.treblereel.gwt.three4g.core.TraverseCallback;
import org.treblereel.gwt.three4g.demo.client.local.AppSetup;
import org.treblereel.gwt.three4g.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.css.GeometryCssClientBundle;
import org.treblereel.gwt.three4g.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.examples.controls.OrbitControls;
import org.treblereel.gwt.three4g.geometries.PlaneBufferGeometry;
import org.treblereel.gwt.three4g.loaders.ObjectLoader;
import org.treblereel.gwt.three4g.loaders.OnLoadCallback;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.scenes.Fog;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/9/18.
 */
public class WebglAnimationScene extends Attachable {

    public static final String name = "animation / scene";
    final static String URL = "json/scene-animation.json";
    private OrbitControls controls;
    private AnimationMixer mixer;
    private Clock clock = new Clock();

    public WebglAnimationScene() {

        GeometryCssClientBundle.IMPL.webglAnimationScene().ensureInjected();

        setupWebGLRenderer(renderer);
        // Load a scene with objects, lights and camera from a JSON file
        new ObjectLoader().load(URL, new OnLoadCallback<JsObject>() {

            @Override
            public void onLoad(JsObject object) {
                scene = object.cast();
                scene.background = new Color(0xffffff);


                AnimationClip[] clips = object.getProperty("animations");

                // If the loaded file contains a perspective camera, use it with adjusted aspect ratio...
                scene.traverse(new TraverseCallback() {
                    @Override
                    public void onEvent(Object3D object) {
                        if (object instanceof PerspectiveCamera) {
                            camera = (PerspectiveCamera) object;
                            camera.aspect = aspect;
                            camera.updateProjectionMatrix();
                        }
                    }
                });
                if (camera == null) {
                    camera = new PerspectiveCamera(30f, aspect, 1f, 10000f);
                    camera.position.set(-200f, 0f, 200f);
                }

                controls = new OrbitControls(camera);

                PlaneBufferGeometry geometry = new PlaneBufferGeometry(20000f, 20000f);
                MeshPhongMaterial material = new MeshPhongMaterial();
                material.shininess = 0.1f;

                Mesh ground = new Mesh(geometry, material);

                ground.position.set(0f, -250f, 0f);
                ground.rotation.x = (float) -Math.PI / 2;
                scene.add(ground);
                scene.fog = new Fog(0xffffff, 1000f, 10000f);

                mixer = new AnimationMixer(scene);
                mixer.clipAction(clips[0]).play();
            }
        }, (e) -> {
        }, (e) -> new IllegalArgumentException("OnErrorCallback"));

    }

    public void doAttachScene() {
        root.appendChild(renderer.domElement);
        onWindowResize();
        animate();
    }

    @Override
    protected void doAttachInfo() {
        AppSetup.infoDiv.show().setHrefToInfo("http://threejs.org").setTextContentToInfo("three.js").setInnetHtml(" webgl - scene animation - <a href=\"https://clara.io/view/96106133-2e99-40cf-8abd-64defd153e61\">Three Gears Scene</a> courtesy of David Sarno\n" +
                "\t\t<br><br>camera orbit/zoom/pan with left/middle/right mouse button");
    }

    private void render() {
        if (mixer != null) {
            mixer.update(0.75f * clock.getDelta());
            renderer.render(scene, camera);
        }
    }

    private void animate() {
        StatsProducer.getStats().update();
        AnimationScheduler.get().requestAnimationFrame(timestamp -> {
            if (root.parentNode != null) {
                render();
                animate();
            }
        });
    }

}
