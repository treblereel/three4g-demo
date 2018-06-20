package org.treblereel.gwt.three4g.demo.client.local.examples.interactive;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.core.client.GWT;
import org.treblereel.gwt.three4g.THREE;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.Raycaster;
import org.treblereel.gwt.three4g.core.extra.Intersect;
import org.treblereel.gwt.three4g.demo.client.api.TrackballControls;
import org.treblereel.gwt.three4g.demo.client.local.AppSetup;
import org.treblereel.gwt.three4g.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.demo.client.local.resources.JavascriptTextResource;
import org.treblereel.gwt.three4g.demo.client.local.utils.JSON;
import org.treblereel.gwt.three4g.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.examples.controls.DragControls;
import org.treblereel.gwt.three4g.geometries.BoxBufferGeometry;
import org.treblereel.gwt.three4g.lights.AmbientLight;
import org.treblereel.gwt.three4g.lights.SpotLight;
import org.treblereel.gwt.three4g.materials.MeshLambertMaterial;
import org.treblereel.gwt.three4g.materials.parameters.MeshLambertMaterialParameters;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.math.Vector2;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.objects.Points;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.renderers.parameters.WebGLRendererParameters;
import org.treblereel.gwt.three4g.scenes.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/14/18.
 */
public class WebglInteractiveDraggableCubes extends Attachable {

    public static final String name = "interactive / draggablecubes";

    private Points particles;
    private Double PARTICLE_SIZE = 20d;
    private Raycaster raycaster;
    private Intersect[] intersects;
    private Vector2 mouse;
    private int INTERSECTED = -1;
    private List<Mesh> objects = new ArrayList();

    private Random rand = new Random();

    private TrackballControls controls;

    public WebglInteractiveDraggableCubes() {

        loadJavaScript(JavascriptTextResource.IMPL.getTrackballControls().getText());

        camera = new PerspectiveCamera(70, window.innerWidth / window.innerHeight, 1, 5000);
        camera.position.z = 1000;

        controls = new TrackballControls(camera);
        controls.rotateSpeed = 1.0f;
        controls.zoomSpeed = 1.2f;
        controls.panSpeed = 0.8f;
        controls.noZoom = false;
        controls.noPan = false;
        controls.staticMoving = true;
        controls.dynamicDampingFactor = 0.3f;

        scene = new Scene();
        scene.background = new Color(0xf0f0f0);
        scene.add(new AmbientLight(0x505050));
        SpotLight light = new SpotLight(0xffffff, 1.5f);
        light.position.set(0, 500, 2000);
        light.angle = (float) Math.PI / 9;
        light.castShadow = true;

        GWT.log(JSON.stringify(light.shadow.camera));

        ((PerspectiveCamera) light.shadow.camera).near = 1000;
        ((PerspectiveCamera) light.shadow.camera).far = 4000;
        light.shadow.mapSize.width = 1024;
        light.shadow.mapSize.height = 1024;
        scene.add(light);

        BoxBufferGeometry geometry = new BoxBufferGeometry(40, 40, 40);
        for (int i = 0; i < 200; i++) {
            MeshLambertMaterialParameters parameters = new MeshLambertMaterialParameters();
            parameters.color = new Color(rand.nextFloat() * 0xffffff);
            Mesh object = new Mesh(geometry, new MeshLambertMaterial(parameters));

            object.position.x = rand.nextFloat() * 1000 - 500;
            object.position.y = rand.nextFloat() * 600 - 300;
            object.position.z = rand.nextFloat() * 800 - 400;
            object.rotation.x = (float) (rand.nextFloat() * 2 * Math.PI);
            object.rotation.y = (float) (rand.nextFloat() * 2 * Math.PI);
            object.rotation.z = (float) (rand.nextFloat() * 2 * Math.PI);
            object.scale.x = rand.nextFloat() * 2 + 1;
            object.scale.y = rand.nextFloat() * 2 + 1;
            object.scale.z = rand.nextFloat() * 2 + 1;
            object.castShadow = true;
            object.receiveShadow = true;
            scene.add(object);
            objects.add(object);
        }

        WebGLRendererParameters webGLRendererParameters = new WebGLRendererParameters();
        webGLRendererParameters.antialias = true;

        webGLRenderer = new WebGLRenderer(webGLRendererParameters);
        webGLRenderer.setSize(window.innerWidth, window.innerHeight);
        webGLRenderer.shadowMap.enabled = true;
        webGLRenderer.shadowMap.type = THREE.PCFShadowMap;
        container.appendChild(webGLRenderer.domElement);

        DragControls dragControls = new DragControls(objects.toArray(new Mesh[objects.size()]), camera, webGLRenderer.domElement);
        dragControls.activate();

        dragControls.addEventListener("dragstart", event -> controls.enabled = false);

        dragControls.addEventListener("dragend", event -> controls.enabled = true);

    }

    public void doAttachScene() {
        root.appendChild(container);
        webGLRenderer.setSize(getWidth(), getHeight());
        animate();
    }

    @Override
    protected void doAttachInfo() {
        AppSetup.infoDiv.show().setHrefToInfo("http://threejs.org").setTextContentToInfo("three.js").setInnetHtml("webgl - draggable cubes' ");
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

    private void render() {
        controls.update();
        webGLRenderer.render(scene, camera);
    }
}

