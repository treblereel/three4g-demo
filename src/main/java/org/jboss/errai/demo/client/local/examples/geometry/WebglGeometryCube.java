package org.jboss.errai.demo.client.local.examples.geometry;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.core.client.GWT;
import elemental2.dom.DomGlobal;
import org.jboss.errai.demo.client.api.Stats;
import org.jboss.errai.demo.client.local.AppSetup;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.resources.JavascriptTextResource;
import org.jboss.errai.demo.client.local.utils.JSON;
import org.jboss.errai.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.geometries.BoxBufferGeometry;
import org.treblereel.gwt.three4g.loaders.TextureLoader;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.materials.parameters.MeshBasicMaterialParameters;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.scenes.Scene;
import org.treblereel.gwt.three4g.textures.Texture;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/7/18.
 */
public class WebglGeometryCube extends Attachable {

    public static final String name = "geometry / cube";

    public WebglGeometryCube() {

        camera = new PerspectiveCamera( 70, aspect, 1, 1000 );
        camera.position.z = 400;

        scene = new Scene();
        Texture texture = new TextureLoader().load( "https://threejs.org/examples/textures/crate.gif");

        BoxBufferGeometry geometry = new BoxBufferGeometry( 200, 200, 200 );
        MeshBasicMaterialParameters meshBasicMaterialParameters = new MeshBasicMaterialParameters();
        meshBasicMaterialParameters.map = texture;
        MeshBasicMaterial material = new MeshBasicMaterial(meshBasicMaterialParameters);

        mesh = new Mesh(geometry, material);
        scene.add(mesh);

        setupWebGLRenderer(webGLRenderer);
    }


    @Override
    public void doAttachScene() {
        root.appendChild(webGLRenderer.domElement);
        onWindowResize();
        animate();
    }

    @Override
    protected void doAttachInfo() {
        AppSetup.infoDiv.hide();

    }

    private void animate() {
        StatsProducer.getStats().update();

        mesh.rotation.x += 0.005;
        mesh.rotation.y += 0.01;

        AnimationScheduler.get().requestAnimationFrame(timestamp -> {
            if (root.parentNode != null) {
                webGLRenderer.render( scene, camera );
                animate();
            }
        });
    }

}
