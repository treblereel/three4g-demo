package org.jboss.errai.demo.client.local.examples.animation;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.core.client.ScriptInjector;
import jsinterop.base.Js;
import jsinterop.base.JsArrayLike;
import jsinterop.base.JsPropertyMap;
import org.jboss.errai.demo.client.local.AppSetup;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.examples.geometry.css.GeometryCssClientBundle;
import org.jboss.errai.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.animation.AnimationClip;
import org.treblereel.gwt.three4g.animation.AnimationMixer;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.Clock;
import org.treblereel.gwt.three4g.core.Object3D;
import org.treblereel.gwt.three4g.core.TraverseCallback;
import org.treblereel.gwt.three4g.examples.controls.OrbitControls;
import org.treblereel.gwt.three4g.examples.resources.ThreeJsExamplesTextResource;
import org.treblereel.gwt.three4g.geometries.PlaneBufferGeometry;
import org.treblereel.gwt.three4g.loaders.ObjectLoader;
import org.treblereel.gwt.three4g.loaders.OnLoadCallback;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.scenes.Fog;
import org.treblereel.gwt.three4g.scenes.Scene;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/9/18.
 */
public class WebglAnimationScene extends Attachable {

    private OrbitControls controls;
    private AnimationMixer mixer;
    private AnimationClip animationClip;

    private Clock clock = new Clock();

    public static final String name = "animation / scene";

    final static String URL = "json/scene-animation.json";

    public WebglAnimationScene() {

        GeometryCssClientBundle.IMPL.webglAnimationScene().ensureInjected();

        setupWebGLRenderer(webGLRenderer);
        // Load a scene with objects, lights and camera from a JSON file
        new ObjectLoader().load(URL, new OnLoadCallback<Object>() {

            @Override
            public void onLoad(Object object) {
                scene = (Scene) object;
                scene.background = new Color(0xffffff);

                JsPropertyMap<Object> javaScriptObject = Js.cast(object);
                JsArrayLike<AnimationClip> array = Js.cast(javaScriptObject.get("animations"));

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
                    camera = new PerspectiveCamera(30, aspect, 1, 10000);
                    camera.position.set(-200, 0, 200);
                }

                controls = new OrbitControls(camera);

                PlaneBufferGeometry geometry = new PlaneBufferGeometry(20000, 20000);
                MeshPhongMaterial material = new MeshPhongMaterial();
                material.shininess = 0.1f;

                Mesh ground = new Mesh(geometry, material);

                ground.position.set(0, -250, 0);
                ground.rotation.x = (float) -Math.PI / 2;
                scene.add(ground);
                scene.fog = new Fog(0xffffff, 1000, 10000);

                animationClip = array.getAt(0);
                mixer = new AnimationMixer(scene);
                mixer.clipAction(animationClip).play();
            }
        }, (e) -> new IllegalArgumentException(e.responseText), () -> new IllegalArgumentException("OnErrorCallback"));

    }

    public void doAttachScene() {
        root.appendChild(webGLRenderer.domElement);
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
            webGLRenderer.render(scene, camera);
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
