package org.treblereel.gwt.three4g.demo.client.local.examples.morph;

import com.google.gwt.animation.client.AnimationScheduler;
import org.treblereel.gwt.three4g.THREE;
import org.treblereel.gwt.three4g.animation.AnimationClip;
import org.treblereel.gwt.three4g.animation.AnimationMixer;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.Clock;
import org.treblereel.gwt.three4g.core.Geometry;
import org.treblereel.gwt.three4g.demo.client.local.AppSetup;
import org.treblereel.gwt.three4g.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.lights.DirectionalLight;
import org.treblereel.gwt.three4g.loaders.JSONLoader;
import org.treblereel.gwt.three4g.loaders.OnLoadCallback;
import org.treblereel.gwt.three4g.materials.MeshLambertMaterial;
import org.treblereel.gwt.three4g.materials.parameters.MeshLambertMaterialParameters;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.math.Vector3;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.renderers.parameters.WebGLRendererParameters;
import org.treblereel.gwt.three4g.scenes.Scene;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 8/9/18.
 */
public class WebglMorphtargetsHorse extends Attachable {

    public static final String name = "morphtargets / horse";
    public AnimationMixer mixer;

    private Clock clock = new Clock();
    private List<AnimationMixer> mixers = new ArrayList<>();

    public WebglMorphtargetsHorse() {

        camera = new PerspectiveCamera(50, aspect, 1, 10000);
        camera.position.y = 300;
        scene = new Scene();
        scene.background = new Color(0xf0f0f0);
        //
        DirectionalLight light = new DirectionalLight(0xefefff, 1.5f);
        light.position.set(1, 1, 1).normalize();
        scene.add(light);
        DirectionalLight directionalLight = new DirectionalLight(0xffefef, 1.5f);
        directionalLight.position.set(-1, -1, -1).normalize();
        scene.add(directionalLight);
        JSONLoader loader = new JSONLoader();
        loader.load("models/animated/horse.js", new OnLoadCallback<Geometry>() {
            @Override
            public void onLoad(Geometry geometry) {

                MeshLambertMaterialParameters parameters = new MeshLambertMaterialParameters();
                parameters.vertexColors = THREE.FaceColors;
                parameters.morphTargets = true;

                mesh = new Mesh(geometry, new MeshLambertMaterial(parameters));
                mesh.scale.set(1.5f, 1.5f, 1.5f);
                scene.add(mesh);
                mixer = new AnimationMixer(mesh);
                AnimationClip clip = AnimationClip.createFromMorphTargetSequence("gallop", geometry.morphTargets, 30, false);
                mixer.clipAction(clip).setDuration(1).play();
            }
        });


        WebGLRendererParameters webGLRendererParameters = new WebGLRendererParameters();
        webGLRendererParameters.antialias = true;
        renderer = new WebGLRenderer(webGLRendererParameters);

        setupWebGLRenderer(renderer);
    }


    @Override
    public void doAttachScene() {
        root.appendChild(renderer.domElement);
        onWindowResize();
        animate();
    }

    @Override
    protected void doAttachInfo() {
        AppSetup.infoDiv.show().setHrefToInfo("http=//threejs.org").setTextContentToInfo("three.js").setInnetHtml(" - <span style='color:orange;' >webgl - morph normals - model by <a style='color:red;' href=\"http://mirada.com/\">mirada</a> from <a href=\"http://ro.me\">rome</a></span>");

    }

    private void animate() {
        AnimationScheduler.get().requestAnimationFrame(timestamp -> {
            if (root.parentNode != null) {
                StatsProducer.getStats().update();
                render();
                animate();
            }
        });
    }

    private double prevTime = 0;
    private float radius = 600, theta = 0;

    private void render() {
        theta += 0.1;
        camera.position.x = radius * (float) Math.sin((double) org.treblereel.gwt.three4g.math.Math.degToRad(theta));
        camera.position.z = radius * (float) Math.cos(org.treblereel.gwt.three4g.math.Math.degToRad(theta));
        camera.lookAt(new Vector3(0, 150, 0));
        new Date().getTime();
        if (mixer != null) {
            double time = new Date().getTime();
            mixer.update((time - prevTime) * 0.001);
            prevTime = time;
        }
        renderer.render(scene, camera);
    }

}



