package org.treblereel.gwt.three4g.demo.client.local.examples.loaders;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.core.client.GWT;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.treblereel.gwt.three4g.animation.AnimationClip;
import org.treblereel.gwt.three4g.animation.AnimationMixer;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.Clock;
import org.treblereel.gwt.three4g.core.Geometry;
import org.treblereel.gwt.three4g.demo.client.local.AppSetup;
import org.treblereel.gwt.three4g.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.lights.AmbientLight;
import org.treblereel.gwt.three4g.lights.PointLight;
import org.treblereel.gwt.three4g.loaders.JSONLoader;
import org.treblereel.gwt.three4g.loaders.OnLoadCallbackPair;
import org.treblereel.gwt.three4g.materials.MeshLambertMaterial;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.scenes.FogExp2;
import org.treblereel.gwt.three4g.scenes.Scene;

import java.util.Date;
import java.util.Random;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel on 6/8/18.
 */
public class WebglLoaderJsonBlender extends Attachable {

    public static final String name = "loader / json / blender";

    private Clock clock = new Clock();
    private AnimationMixer mixer;
    private JSONLoader loader;
    private Random rand = new Random();

    public WebglLoaderJsonBlender() {

        camera = new PerspectiveCamera(50, aspect, 1, 2000);
        camera.position.set(2, 4, 5);
        scene = new Scene();
        scene.fog = new FogExp2(new Color(0x000000), 0.035f);
        mixer = new AnimationMixer(scene);
        loader = new JSONLoader();
        loader.load("models/animated/monster/monster.js", new OnLoadCallbackPair<Geometry, MeshLambertMaterial[]>() {
            @Override
            public void onLoad(Geometry geometry, MeshLambertMaterial[] map) {
                MeshLambertMaterial material = map[0];

                material.morphTargets = true;
                material.color.setHex(0xffaaaa);
                for (int i = 0; i < 729; i++) {
                    Mesh mesh = new Mesh(geometry, material);
                    // random placement in a grid
                    float x = ((i % 27f) - 13.5f) * 2 + org.treblereel.gwt.three4g.math.Math.randFloatSpread(1f);
                    float z = ((float) Math.floor(i / 27f) - 13.5f) * 2 + org.treblereel.gwt.three4g.math.Math.randFloatSpread(1);
                    mesh.position.set(x, 0, z);
                    float s = org.treblereel.gwt.three4g.math.Math.randFloat(0.00075f, 0.001f);
                    mesh.scale.set(s, s, s);
                    mesh.rotation.y = org.treblereel.gwt.three4g.math.Math.randFloat(-0.25f, 0.25f);
                    mesh.matrixAutoUpdate = false;
                    mesh.updateMatrix();
                    scene.add(mesh);

                    AnimationClip[] clips = geometry.getProperty("animations");

                    mixer.clipAction(clips[0], mesh)
                            .setDuration(1)           // one second
                            .startAt(-rand.nextDouble()) // random phase (already running)
                            .play();                    // let's go
                }
            }
        });


        // lights
        AmbientLight ambientLight = new AmbientLight(0xcccccc);
        scene.add(ambientLight);
        PointLight pointLight = new PointLight(0xff4400, 5, 30);
        pointLight.position.set(5, 0, 0);
        scene.add(pointLight);
        // renderer
        webGLRenderer = new WebGLRenderer();
        webGLRenderer.setSize(window.innerWidth, window.innerHeight);
        container.appendChild(webGLRenderer.domElement);
    }


    @Override
    protected void doAttachScene() {
        root.appendChild(container);
        webGLRenderer.setSize(getWidth(), getHeight());
        animate();
    }

    @Override
    protected void doAttachInfo() {
        AppSetup.infoDiv.show().setHrefToInfo("http://threejs.org").setTextContentToInfo("three.js").setInnetHtml("</a> -\n" +
                "                monster by <a href=\"http://www.3drt.com/downloads.htm\" target=\"_blank\" rel=\"noopener\">3drt</a>");


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

    private void render() {
        double timer = new Date().getTime() * 0.0005;

        camera.position.x = (float) Math.cos(timer) * 10;
        camera.position.y = 4;
        camera.position.z = (float) Math.sin(timer) * 10;
        mixer.update(clock.getDelta());
        camera.lookAt(scene.position);
        webGLRenderer.render(scene, camera);
    }
}
