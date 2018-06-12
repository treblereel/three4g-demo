package org.treblereel.gwt.three4g.demo.client.local.examples.loaders;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.core.client.GWT;
import elemental2.dom.DomGlobal;
import elemental2.dom.Event;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.MouseEvent;
import jsinterop.base.Js;
import org.treblereel.gwt.three4g.animation.AnimationMixer;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.BufferGeometry;
import org.treblereel.gwt.three4g.core.Clock;
import org.treblereel.gwt.three4g.core.Geometry;
import org.treblereel.gwt.three4g.core.JsObject;
import org.treblereel.gwt.three4g.demo.client.local.AppSetup;
import org.treblereel.gwt.three4g.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.demo.client.local.utils.JSON;
import org.treblereel.gwt.three4g.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.examples.controls.OrbitControls;
import org.treblereel.gwt.three4g.examples.loaders.PRWMLoader;
import org.treblereel.gwt.three4g.lights.AmbientLight;
import org.treblereel.gwt.three4g.lights.DirectionalLight;
import org.treblereel.gwt.three4g.loaders.OnLoadCallback;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.math.Vector2;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.renderers.parameters.WebGLRendererParameters;
import org.treblereel.gwt.three4g.scenes.Scene;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/12/18.
 */
public class WebglLoaderPrwm extends Attachable {

    public static final String name = "loader / prwm";

    private boolean busy = false;
    private PRWMLoader loader;
    private MeshPhongMaterial material = new MeshPhongMaterial();
    private Mesh mesh;
    private Vector2 mouse = new Vector2();

    public WebglLoaderPrwm() {

        camera = new PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 2000);
        camera.position.z = 250;
        // scene
        scene = new Scene();
        AmbientLight ambient = new AmbientLight(0x101030);
        scene.add(ambient);
        DirectionalLight directionalLight = new DirectionalLight(0xffeedd);
        directionalLight.position.set(0, 0, 1);
        scene.add(directionalLight);
        // model
        loader = new PRWMLoader();

        WebGLRendererParameters webGLRendererParameters = new WebGLRendererParameters();
        webGLRendererParameters.antialias = true;


        webGLRenderer = new WebGLRenderer(webGLRendererParameters);
        webGLRenderer.setPixelRatio(1);
        webGLRenderer.setSize(window.innerWidth, window.innerHeight);
        webGLRenderer.shadowMap.enabled = true;
        //
        container.appendChild(webGLRenderer.domElement);


        window.onmousemove = p0 -> {
            MouseEvent event = Js.uncheckedCast(p0);
            onDocumentMouseMove(event);
            return null;
        };
        loadGeometry("models/prwm/smooth-suzanne.*.prwm");

    }

    private void loadGeometry(String url) {
        if (busy) return;
        busy = true;
        if (mesh != null) {
            scene.remove(mesh);
            mesh.geometry.dispose();
        }
        GWT.log("-- Loading" + url);

        loader.load(url, new OnLoadCallback<BufferGeometry>() {
            @Override
            public void onLoad(BufferGeometry geometry) {
                mesh = new Mesh(geometry, material);
                mesh.scale.set(50, 50, 50);
                scene.add(mesh);

                //GWT.log( geometry.getProperty("index") ? "indexed geometry" : "non-indexed geometry" );
                //GWT.log( "# of vertices: " + geometry.attributes.position.count );
                //GWT.log( "# of polygons: " + ( geometry.index ? geometry.index.count / 3 : geometry.attributes.position.count / 3 ) );
                busy = false;
            }
        }, request -> {
            if (request.lengthComputable) {
                float percentComplete = request.loaded / request.total * 100;
                GWT.log(Math.round(percentComplete) + "% downloaded");
                if (request.loaded == request.total) {
                    GWT.log("File size: " + (request.total / 1024) + "kB");
                }
            }


        }, error -> busy = false);


    }

    @Override
    protected void doAttachScene() {
        root.appendChild(container);
        webGLRenderer.setSize(getWidth(), getHeight());
        animate();
    }

    @Override
    protected void doAttachInfo() {
        HTMLDivElement models = (HTMLDivElement)document.createElement("div");
        models.className = "models";

        models.appendChild(createModel("Faceted Nefertiti","models/prwm/faceted-nefertiti.*.prwm"));
        models.appendChild(createModel("Smooth Suzanne","models/prwm/smooth-suzanne.*.prwm"));
        models.appendChild(createModel("Vive Controller","models/prwm/vive-controller.*.prwm"));

        AppSetup.infoDiv.show().setTextContentToInfo(" loader prwm").addElement(models);


    }

    private HTMLAnchorElement createModel(String text, String href){
        HTMLAnchorElement model =  (HTMLAnchorElement)document.createElement("a");
        model.className = "model";
        model.innerHTML = text;
        model.setAttribute("url", href);
        model.addEventListener("click", new EventListener() {
            @Override
            public void handleEvent(Event evt) {
                loadGeometry(model.getAttribute("url"));
            }
        });
        return model;
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
        camera.position.x += (mouse.x - camera.position.x) * 0.05;
        camera.position.y += (-mouse.y - camera.position.y) * 0.05;

        camera.lookAt(scene.position);
        webGLRenderer.render(scene, camera);
    }

    private void onDocumentMouseMove(MouseEvent event) {
        mouse.x = (float)(( event.clientX - (window.innerWidth) / 2) / 2);
        mouse.y = (float)(( event.clientY - (window.innerHeight / 2 )) / 2);
    }

}