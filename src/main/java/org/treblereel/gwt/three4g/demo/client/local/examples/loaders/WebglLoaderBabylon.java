package org.treblereel.gwt.three4g.demo.client.local.examples.loaders;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.core.client.GWT;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.demo.client.api.TrackballControls;
import org.treblereel.gwt.three4g.demo.client.local.AppSetup;
import org.treblereel.gwt.three4g.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.demo.client.local.resources.JavascriptTextResource;
import org.treblereel.gwt.three4g.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.examples.loaders.BabylonLoader;
import org.treblereel.gwt.three4g.loaders.managers.LoadingManager;
import org.treblereel.gwt.three4g.loaders.managers.OnProgress;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.materials.parameters.MeshPhongMaterialParameters;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.scenes.Scene;

import java.util.Random;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/10/18.
 */
public class WebglLoaderBabylon extends Attachable {

    public static final String name = "loader / babylon";
    private TrackballControls controls;

    public WebglLoaderBabylon() {

        loadJavaScript(JavascriptTextResource.IMPL.getTrackballControls().getText());

        camera = new PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 2000);
        camera.position.z = 100;
        controls = new TrackballControls(camera);
        // scene
        scene = new Scene();
        // texture
        LoadingManager manager = new LoadingManager();

        manager.onProgress = new OnProgress() {
            @Override
            public void onProgress(String url, int itemsLoaded, int itemsTotal) {
                GWT.log(" " + url + " " + itemsLoaded + " " + itemsTotal);

            }
        };

        MeshBasicMaterial material = new MeshBasicMaterial();
        material.color = new Color("red");

        // model
        BabylonLoader loader = new BabylonLoader(manager);
        loader.load("models/babylon/skull.babylon", object -> {
            Scene babylonScene = object.cast();
            babylonScene.traverse(object1 -> {
                MeshPhongMaterialParameters parameters = new MeshPhongMaterialParameters();
                parameters.color = new Color(new Random().nextInt() * 0xffffff);
                if (object1 instanceof Mesh) {
                    ((Mesh) object1).material = new MeshPhongMaterial(parameters);
                }
            });
            scene = babylonScene;
            animate();

        });

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
        AppSetup.infoDiv.show().setHrefToInfo("http://threejs.org").setTextContentToInfo("three.js").setInnetHtml(" - BabylonLoader test");


    }

    private void animate() {
        AnimationScheduler.get().requestAnimationFrame(timestamp -> {
            if (root.parentNode != null) {
                StatsProducer.getStats().update();
                controls.update();
                webGLRenderer.render(scene, camera);
                animate();

            }
        });
    }

}
