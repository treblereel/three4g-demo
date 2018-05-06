package org.jboss.errai.demo.client.local.examples.camera;

import com.google.gwt.animation.client.AnimationScheduler;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.ioc.client.api.LoadAsync;
import org.slf4j.Logger;
import org.treblereel.gwt.three4g.cameras.ArrayCamera;
import org.treblereel.gwt.three4g.cameras.OrthographicCamera;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.geometries.CylinderBufferGeometry;
import org.treblereel.gwt.three4g.geometries.PlaneBufferGeometry;
import org.treblereel.gwt.three4g.lights.AmbientLight;
import org.treblereel.gwt.three4g.lights.DirectionalLight;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.math.Vector3;
import org.treblereel.gwt.three4g.math.Vector4;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.scenes.Scene;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/21/18.
 */
@LoadAsync
@ApplicationScoped
public class WebglCameraArray extends Attachable {

    @Inject
    Logger logger;

    Mesh mesh;

    @PostConstruct
    public void init() {
        scene = new Scene();

        float AMOUNT = 6;
        float SIZE = 1 / AMOUNT;
        List<PerspectiveCamera> cameras = new ArrayList<>();
        for ( int y = 0; y < AMOUNT; y ++ ) {
            for ( int x = 0; x < AMOUNT; x ++ ) {
                PerspectiveCamera subcamera = new PerspectiveCamera( 40, aspect, 0.1f, 10 );
                subcamera.bounds = new Vector4( x / AMOUNT, y / AMOUNT, SIZE, SIZE );
                subcamera.position.x = ( x / AMOUNT ) - 0.5f;
                subcamera.position.y = 0.5f - ( y / AMOUNT );
                subcamera.position.z = 1.5f;
                subcamera.position.multiplyScalar( 2 );
                subcamera.lookAt( new Vector3() );
                subcamera.updateMatrixWorld(true);
                cameras.add(subcamera);
            }
        }
        camera = new ArrayCamera( cameras.toArray(new PerspectiveCamera[cameras.size()]));
        camera.position.z = 3;
        scene = new Scene();
        scene.add( new AmbientLight( 0x222244 ) );

        DirectionalLight light = new DirectionalLight();
        light.position.set(0.5f, 0.5f, 1 );
        light.castShadow = true;
        ((OrthographicCamera)light.shadow.camera).zoom = 4; // tighter shadow map
        scene.add(light);

        PlaneBufferGeometry geometry = new PlaneBufferGeometry( 100, 100 );
        MeshPhongMaterial material = new MeshPhongMaterial();
        material.color = new Color(0x000066);
        Mesh background = new Mesh(geometry, material);
        background.receiveShadow = true;
        background.position.set( 0, 0, - 1 );
        scene.add( background );

        CylinderBufferGeometry cylinderBufferGeometry = new CylinderBufferGeometry( 0.5f, 0.5f, 1f, 32 );
        material = new MeshPhongMaterial();
        material.color = new Color(0xff0000);
        mesh = new Mesh(cylinderBufferGeometry, material);
        mesh.castShadow = true;
        mesh.receiveShadow = true;
        scene.add(mesh);

        webGLRenderer = new WebGLRenderer();
        setupWebGLRenderer(webGLRenderer);
        //webGLRenderer.setPixelRatio( window.devicePixelRatio );
        webGLRenderer.shadowMap.enabled = true;
        //
        window.addEventListener("resize", evt -> onWindowResize(), false);

    }

    private void animate() {
        AnimationScheduler.get().requestAnimationFrame(timestamp -> {
            if (webGLRenderer.domElement.parentNode != null) {
                render();
                animate();
            }
        });
    }

    private void render() {
        mesh.rotation.x += 0.005;
        mesh.rotation.z += 0.01;
        webGLRenderer.render(scene, camera);
    }

    @Override
    protected void doAttachScene() {
        document.body.appendChild(webGLRenderer.domElement);
        onWindowResize();
        animate();
    }

    @Override
    protected void doAttachInfo() {

    }
}
