package org.jboss.errai.demo.client.local.examples.geometry;

import com.google.gwt.animation.client.AnimationScheduler;
import org.jboss.errai.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.geometries.BoxBufferGeometry;
import org.treblereel.gwt.three4g.loaders.TextureLoader;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterialParameters;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.scenes.Scene;
import org.treblereel.gwt.three4g.textures.Texture;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/7/18.
 */
@ApplicationScoped
public class WebglGeometryCube extends Attachable {

    @PostConstruct
    public void init() {

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

        webGLRenderer = new WebGLRenderer();
        setupWebGLRenderer(webGLRenderer);

        window.addEventListener("resize", evt -> onWindowResize(), false);
    }


    @Override
    public void doAttachScene() {
        document.body.appendChild(webGLRenderer.domElement);
        onWindowResize();
        animate();
    }

    @Override
    protected void doAttachInfo() {

    }

    private void animate() {

        mesh.rotation.x += 0.005;
        mesh.rotation.y += 0.01;
        webGLRenderer.render( scene, camera );

        AnimationScheduler.get().requestAnimationFrame(timestamp -> {
            if (webGLRenderer.domElement.parentNode != null) {
                animate();
            }
        });
    }

}
