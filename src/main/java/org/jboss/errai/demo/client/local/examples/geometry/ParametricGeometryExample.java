package org.jboss.errai.demo.client.local.examples.geometry;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.inject.Inject;
import org.jboss.errai.demo.client.api.OrbitControls;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.resources.JavascriptTextResource;
import org.jboss.errai.ioc.client.api.LoadAsync;
import org.slf4j.Logger;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.geometries.ParametricGeometry;
import org.treblereel.gwt.three4g.geometries.ParametricGeometryFunction;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.math.Matrix4;
import org.treblereel.gwt.three4g.math.Vector3;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.scenes.Scene;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import static elemental2.dom.DomGlobal.document;

/**
 * Based on example https://codepen.io/znak/pen/OPZwVO
 *
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/27/18.
 */
@LoadAsync
@ApplicationScoped
public class ParametricGeometryExample extends Attachable {


    private OrbitControls controls;
    private ParametricGeometry geometry;
    private MeshBasicMaterial material;
    private int d = 16;
    private ParametricGeometryFunction ellipticParaboloid, paramFunc;

    @Inject
    private Logger logger;

    @PostConstruct
    public void init() {

        loadJavaScript(JavascriptTextResource.IMPL.getOrbitControls().getText());
        loadJavaScript(JavascriptTextResource.IMPL.getParametricGeometries().getText());

/*        scene = new Scene();
        scene.background = new Color(0x00ff00);
        camera = new PerspectiveCamera(175, aspect, 0.1f, 150f);
        camera.position.z = 30;

        Geometry geometry = new ParametricGeometry(ParametricGeometries.klein, 25, 25 );
        MeshBasicMaterial material = new MeshBasicMaterial();
        material.color = new Color(0x00ff00);
        mesh = new Mesh(geometry, material);
        scene.add( mesh );*/




/*
        scene = new Scene();
        camera = new PerspectiveCamera(75, aspect, 0.1f, 50f);
        camera.position.z = 30;

        PointLight[] lights = new PointLight[3];
        lights[0] = new PointLight(0xffffff, 1, 0);
        lights[1] = new PointLight(0xffffff, 1, 0);
        lights[2] = new PointLight(0xffffff, 1, 0);

        lights[0].position.set(0, 200, 0);
        lights[1].position.set(100, 200, 100);
        lights[2].position.set(-100, -200, -100);

        scene.add(lights[0]);
        scene.add(lights[1]);
        scene.add(lights[2]);

        Object3D mesh = new Object3D();

        LineBasicMaterialParameters lineBasicMaterialParameters = new LineBasicMaterialParameters();
        lineBasicMaterialParameters.color = new Color(0xffffff);
        lineBasicMaterialParameters.transparent = true;
        lineBasicMaterialParameters.opacity = 0.5f;

        mesh.add(new LineSegments(
                new Geometry(),
                new LineBasicMaterial(lineBasicMaterialParameters)

        ));

        MeshPhongMaterialParameters meshPhongMaterialParameters = new MeshPhongMaterialParameters();
        meshPhongMaterialParameters.color = new Color(0xffffff);
        meshPhongMaterialParameters.emissive = new Color(0x072534);
        meshPhongMaterialParameters.side = DoubleSide;
        meshPhongMaterialParameters.flatShading = true;

        mesh.add(new Mesh(
                new Geometry(),
                new MeshPhongMaterial(meshPhongMaterialParameters)
        ));

        //var options = chooseFromHash( mesh );

        scene.add(mesh);

        boolean prevFog = false;
*/






































        scene = new Scene();

        camera = new PerspectiveCamera( 75, aspect, 1, 10000 );
        camera.position.z = 1000;

        controls = new OrbitControls(camera);

        ellipticParaboloid = new ParametricGeometryFunction() {
            @Override
            public Vector3 call(float u, float v) {

                u *= 1;
                v *= 360 * Math.PI / 180;

                int a = 200; // semimajor axis a
                int b = 400; // semimajor axis b
                int h = 350; // height

                double x = a * Math.sqrt( u ) * Math.cos( v );
                double y = b * Math.sqrt( u ) * Math.sin( v );
                double z = u * h;

                logger.warn("called ellipticParaboloid " + x + " " + y + " " + z);

                return new Vector3((float) x,(float) y,(float) z );
            }
        };

        paramFunc = new ParametricGeometryFunction() {
            @Override
            public Vector3 call(float u, float v) {

                u *= 1;
                v *= 240 * Math.PI / 180;

                int a = 300; // semimajor axes
                int h = 300; // height
                double m = Math.sin( u * ( 135 * Math.PI / 180 ) ); // mod

                double x = a * Math.sqrt( u ) * Math.cos( v ) * m;
                double y = a * Math.sqrt( u ) * Math.sin( v ) * m;
                double z = u * h;

                return new Vector3((float) x,(float) y,(float) z );
            }
        };

        geometry = new ParametricGeometry( ellipticParaboloid, d/2, d );

        geometry.computeBoundingBox();
        geometry.applyMatrix( new Matrix4().makeTranslation( -250f, 120f, -250 ) );

        material = new MeshBasicMaterial();
        material.color = new Color(0xffff00);
        material.wireframe = true;
        mesh = new Mesh( geometry, material );
        scene.add( mesh );

        webGLRenderer = new WebGLRenderer();
        setupWebGLRenderer(webGLRenderer);
        //webGLRenderer.setClearColor( new Color(0x00ff00), 1 );
        controls = new OrbitControls(camera, webGLRenderer.domElement);
        controls.enableZoom = false;
        window.addEventListener("resize", evt -> onWindowResize(), false);
    }

/*    private void chooseFromHash(Mesh mesh ) {

        var selectedGeometry = window.location.hash.substring( 1 ) || "TorusGeometry";

        if ( guis[ selectedGeometry ] !== undefined ) {

            guis[ selectedGeometry ]( mesh );

        }

        if ( selectedGeometry === 'TextGeometry' || selectedGeometry === 'TextBufferGeometry' ) {

            return { fixed: true };

        }

        //No configuration options
        return {};

    }*/

    @Override
    protected void doAttachScene() {
        document.body.appendChild(webGLRenderer.domElement);
        onWindowResize();
        animate();
    }

    @Override
    protected void doAttachInfo() {

    }

    private void animate() {

      //  mesh.rotation.x += 0.005;
      //  mesh.rotation.y += 0.005;

        mesh.rotation.y += 0.002;
        controls.update();

        AnimationScheduler.get().requestAnimationFrame(timestamp -> {
            if (webGLRenderer.domElement.parentNode != null) {
                render();
                animate();
            }
        });
    }

    private void render() {
        webGLRenderer.render(scene, camera);
    }
}
