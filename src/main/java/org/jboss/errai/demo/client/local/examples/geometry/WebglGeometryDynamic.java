package org.jboss.errai.demo.client.local.examples.geometry;

import com.google.gwt.animation.client.AnimationScheduler;
import org.jboss.errai.demo.client.api.FirstPersonControls;
import org.jboss.errai.demo.client.local.AppSetup;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.examples.geometry.css.GeometryCssClientBundle;
import org.jboss.errai.demo.client.local.resources.JavascriptTextResource;
import org.jboss.errai.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.THREE;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.Clock;
import org.treblereel.gwt.three4g.core.Geometry;
import org.treblereel.gwt.three4g.geometries.PlaneGeometry;
import org.treblereel.gwt.three4g.loaders.TextureLoader;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.materials.parameters.MeshBasicMaterialParameters;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.scenes.FogExp2;
import org.treblereel.gwt.three4g.scenes.Scene;
import org.treblereel.gwt.three4g.textures.Texture;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel on 3/8/18.
 */
public class WebglGeometryDynamic extends Attachable {


    private FirstPersonControls controls;
    private PlaneGeometry geometry;

    public static final String name = "geometry / dynamic";


    int worldWidth = 128;
    int worldDepth = 128;

    private Clock clock = new Clock();


    public WebglGeometryDynamic() {
        loadJavaScript(JavascriptTextResource.IMPL.getFirstPersonControls().getText());
        GeometryCssClientBundle.IMPL.webglAnimationScene().ensureInjected();

        camera = new PerspectiveCamera(60, aspect, 1, 20000);
        camera.position.y = 200;

        controls = new FirstPersonControls(camera);
        controls.movementSpeed = 500;
        controls.lookSpeed = 0.1;

        scene = new Scene();
        scene.background = new Color(0xaaccff);
        scene.fog = new FogExp2(new Color(0xaaccff), 0.0007f);

        geometry = new PlaneGeometry(20000, 20000, worldWidth - 1, worldDepth - 1);
        geometry.rotateX(new Float(-Math.PI / 2));

        for (int i = 0, l = geometry.vertices.length; i < l; i++) {
            geometry.vertices[i].y = new Float(35 * Math.sin(i / 2));
        }
        Texture texture = new TextureLoader().load("img/water.jpg");

        texture.wrapS = texture.wrapT = THREE.RepeatWrapping;
        texture.repeat.set(5, 5);

        MeshBasicMaterialParameters meshBasicMaterialParameters = new MeshBasicMaterialParameters();

        meshBasicMaterialParameters.color = new Color(0x0044ff);
        meshBasicMaterialParameters.map = texture;

        MeshBasicMaterial material = new MeshBasicMaterial(meshBasicMaterialParameters);
        mesh = new Mesh(geometry, material);
        scene.add(mesh);
        setupWebGLRenderer(webGLRenderer);
    }

    private void render() {
        float delta = clock.getDelta();
        float time = clock.getElapsedTime() * 10;

        for (int i = 0, l = geometry.vertices.length; i < l; i++) {
            geometry.vertices[i].y = new Float(35 * Math.sin(i / 5 + (time + i) / 7));
        }
        ((Geometry)mesh.geometry).verticesNeedUpdate = true;
        controls.update(delta);
        webGLRenderer.render(scene, camera);
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

    @Override
    public void doAttachScene() {
        root.appendChild(webGLRenderer.domElement);
        onWindowResize();
        animate();
    }

    @Override
    protected void doAttachInfo() {
        AppSetup.infoDiv.show().setHrefToInfo("http://threejs.org").setTextContentToInfo("three.js").setInnetHtml(" - dynamic geometry demo - webgl<br />(left click: forward, right click: backward)");
    }

}
