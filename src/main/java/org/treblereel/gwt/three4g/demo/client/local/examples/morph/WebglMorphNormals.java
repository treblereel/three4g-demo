package org.treblereel.gwt.three4g.demo.client.local.examples.morph;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.core.client.GWT;
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
import org.treblereel.gwt.three4g.lights.HemisphereLight;
import org.treblereel.gwt.three4g.loaders.JSONLoader;
import org.treblereel.gwt.three4g.loaders.OnLoadCallback;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.materials.parameters.MeshPhongMaterialParameters;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.math.Vector3;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.renderers.parameters.WebGLRendererParameters;
import org.treblereel.gwt.three4g.scenes.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 8/9/18.
 */
public class WebglMorphNormals extends Attachable {

    public static final String name = "morphnormals";

    private Clock clock = new Clock();
    private List<AnimationMixer> mixers = new ArrayList<>();

    public WebglMorphNormals() {

        camera = new PerspectiveCamera(40, aspect, 1, 10000);
        camera.position.y = 300;
        //camera.target = new Vector3(0, 150, 0);
        scene = new Scene();
        //
        scene.add(new HemisphereLight(0x443333, 0x222233));
        DirectionalLight light = new DirectionalLight(0xffffff, 1);
        light.position.set(1, 1, 1);
        scene.add(light);
        //
        JSONLoader loader = new JSONLoader();
        loader.load("models/animated/flamingo.js", (OnLoadCallback<Geometry>) geometry -> {

            MeshPhongMaterialParameters parameters = new MeshPhongMaterialParameters();
            parameters.color = new Color(0xffffff);
            parameters.vertexColors = THREE.FaceColors;
            parameters.morphTargets = true;
            parameters.flatShading = true;

            MeshPhongMaterial material = new MeshPhongMaterial(parameters);

            Mesh mesh = new Mesh(geometry, material);
            mesh.position.x = -150;
            mesh.position.y = 150;
            mesh.scale.set(1.5f, 1.5f, 1.5f);
            scene.add(mesh);

            AnimationMixer mixer = new AnimationMixer(mesh);
            AnimationClip[] animations = geometry.getProperty("animations");
            mixer.clipAction(animations[0]).setDuration(1).play();
            mixers.add(mixer);

        });

        loader.load("models/animated/flamingo.js", (OnLoadCallback<Geometry>) geometry -> {

            geometry.computeVertexNormals();
            geometry.computeMorphNormals();

            MeshPhongMaterialParameters meshPhongMaterialParameters = new MeshPhongMaterialParameters();
            meshPhongMaterialParameters.color = new Color(0xffffff);
            meshPhongMaterialParameters.morphTargets = true;
            meshPhongMaterialParameters.morphNormals = true;
            meshPhongMaterialParameters.vertexColors = THREE.FaceColors;

            MeshPhongMaterial material = new MeshPhongMaterial(meshPhongMaterialParameters);

            Mesh mesh = new Mesh(geometry, material);
            mesh.position.x = 150;
            mesh.position.y = 150;
            mesh.scale.set(1.5f, 1.5f, 1.5f);
            scene.add(mesh);

            AnimationMixer mixer = new AnimationMixer(mesh);
            AnimationClip[] animations = geometry.getProperty("animations");
            mixer.clipAction(animations[0]).setDuration(1).play();
            mixers.add(mixer);

        });


        WebGLRendererParameters webGLRendererParameters = new WebGLRendererParameters();
        webGLRendererParameters.antialias = true;
        renderer = new WebGLRenderer(webGLRendererParameters);

        setupWebGLRenderer(renderer);
        container.appendChild(renderer.domElement);

    }


    @Override
    public void doAttachScene() {
        root.appendChild(renderer.domElement);
        onWindowResize();
        animate();
    }

    @Override
    protected void doAttachInfo() {
        AppSetup.infoDiv.show().setHrefToInfo("http=//threejs.org").setTextContentToInfo("three.js").setInnetHtml(" -webgl - morph normals - model by <a style='color:orange;' href=\"http://mirada.com/\">mirada</a> from <a href=\"http://ro.me\">rome</a>");

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

    private float radius = 600;
    private float theta = 0;

    private void render() {
        theta += 0.1;
        camera.position.x = radius * (float) Math.sin((double) org.treblereel.gwt.three4g.math.Math.degToRad(theta));
        camera.position.z = radius * (float) Math.cos(org.treblereel.gwt.three4g.math.Math.degToRad(theta));
        camera.lookAt(new Vector3( 0, 150, 0 ));
        float delta = clock.getDelta();
        for (int i = 0; i < mixers.size(); i++) {
            mixers.get(i).update(delta);
        }
        renderer.clear();
        renderer.render(scene, camera);
    }

}


