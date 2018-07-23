package org.treblereel.gwt.three4g.demo.client.local.examples.geometry;

import com.google.gwt.animation.client.AnimationScheduler;
import elemental2.dom.MouseEvent;
import jsinterop.base.Js;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.Geometry;
import org.treblereel.gwt.three4g.demo.client.local.AppSetup;
import org.treblereel.gwt.three4g.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.lights.DirectionalLight;
import org.treblereel.gwt.three4g.loaders.JSONLoader;
import org.treblereel.gwt.three4g.loaders.OnLoadCallbackPair;
import org.treblereel.gwt.three4g.materials.Material;
import org.treblereel.gwt.three4g.math.Vector2;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.parameters.WebGLRendererParameters;
import org.treblereel.gwt.three4g.scenes.Scene;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 7/12/18.
 */
public class WebglGeometryColorsJson extends Attachable {

    public static final String name = "geometry / colors / json";
    private Vector2 mouse = new Vector2();
    private DirectionalLight light;
    private Mesh mesh2;

    public WebglGeometryColorsJson() {

        camera = new PerspectiveCamera(40, window.innerWidth / window.innerHeight, 1, 10000);
        camera.position.z = 1800;
        scene = new Scene();
        light = new DirectionalLight(0xffffff);
        light.position.set(0, 0, 1).normalize();
        scene.add(light);
        JSONLoader loader = new JSONLoader();
        loader.load("models/json/cubecolors/cubecolors.json", (OnLoadCallbackPair<Geometry, Material>) (object, object2) -> {
            createScene1(object, object2);
        });
        loader.load("models/json/cubecolors/cube_fvc.json", (OnLoadCallbackPair<Geometry, Material>) (object, object2) -> createScene2(object, object2));

        document.addEventListener("mousemove", evt -> onDocumentMouseMove(Js.uncheckedCast(evt)), false);

        WebGLRendererParameters parameters = new WebGLRendererParameters();
        parameters.antialias = true;
        setupWebGLRenderer(renderer);

    }

    public void createScene1(Geometry geometry, Material materials) {
        mesh = new Mesh(geometry, materials);
        mesh.position.x = 400;
        mesh.scale.x = mesh.scale.y = mesh.scale.z = 250;
        scene.add(mesh);
    }

    public void createScene2(Geometry geometry, Material materials) {
        mesh2 = new Mesh(geometry, materials);
        mesh2.position.x = -400;
        mesh2.scale.x = mesh2.scale.y = mesh2.scale.z = 250;
        scene.add(mesh2);
    }

    double windowHalfX, windowHalfY;

    @Override
    public void onWindowResize() {
        super.onWindowResize();
        windowHalfX = getWidth() / 2;
        windowHalfY = getHeight() / 2;
    }


    public void onDocumentMouseMove(MouseEvent event) {
        mouse.x = (float) (event.clientX - windowHalfX);
        mouse.y = (float) (event.clientY - windowHalfY);
    }

    private void render() {
        camera.position.x += (mouse.x - camera.position.x) * 0.05;
        camera.position.y += (-mouse.y - camera.position.y) * 0.05;
        camera.lookAt(scene.position);
        if (mesh != null) {
            mesh.rotation.x += 0.01;
            mesh.rotation.y += 0.01;
        }
        if (mesh2 != null) {
            mesh2.rotation.x += 0.01;
            mesh2.rotation.y += 0.01;
        }
        renderer.render(scene, camera);
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

    @Override
    public void doAttachScene() {
        root.appendChild(renderer.domElement);
        onWindowResize();
        animate();
    }

    @Override
    protected void doAttachInfo() {
        AppSetup.infoDiv.show().setHrefToInfo("http://threejs.org").setTextContentToInfo("three.js").setInnetHtml("  webgl - json - vertex colors");
    }
}


