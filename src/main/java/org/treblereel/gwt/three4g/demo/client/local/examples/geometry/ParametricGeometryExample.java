package org.treblereel.gwt.three4g.demo.client.local.examples.geometry;

import com.google.gwt.animation.client.AnimationScheduler;
import org.treblereel.gwt.three4g.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.demo.client.local.resources.JavascriptTextResource;
import org.treblereel.gwt.three4g.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.examples.controls.OrbitControls;
import org.treblereel.gwt.three4g.geometries.ParametricGeometry;
import org.treblereel.gwt.three4g.geometries.ParametricGeometryFunction;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.math.Matrix4;
import org.treblereel.gwt.three4g.math.Vector3;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.scenes.Scene;


import static elemental2.dom.DomGlobal.document;

/**
 * Based on example https://codepen.io/znak/pen/OPZwVO
 *
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/27/18.
 */
public class ParametricGeometryExample extends Attachable {


    private OrbitControls controls;
    private ParametricGeometry geometry;
    private MeshBasicMaterial material;
    private int d = 16;
    private ParametricGeometryFunction ellipticParaboloid, paramFunc;


    public ParametricGeometryExample() {

        loadJavaScript(JavascriptTextResource.IMPL.getParametricGeometries().getText());

        scene = new Scene();

        camera = new PerspectiveCamera( 75f, aspect, 1f, 10000f );
        camera.position.z = 1000f;

        controls = new OrbitControls(camera);

        ellipticParaboloid = new ParametricGeometryFunction() {
            @Override
            public Vector3 call(float u, float v) {

                u *= 1;
                v *= (float)(360 * Math.PI / 180);

                int a = 200; // semimajor axis a
                int b = 400; // semimajor axis b
                int h = 350; // height

                double x = a * Math.sqrt( u ) * Math.cos( v );
                double y = b * Math.sqrt( u ) * Math.sin( v );
                double z = u * h;


                return new Vector3((float) x,(float) y,(float) z );
            }
        };

        paramFunc = new ParametricGeometryFunction() {
            @Override
            public Vector3 call(float u, float v) {

                u *= 1;
                v *= (float)(240 * Math.PI / 180);

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
        geometry.applyMatrix( new Matrix4().makeTranslation( -250f, 120f, -250f ) );

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
        mesh.rotation.y += 0.002f;
        controls.update();

        AnimationScheduler.get().requestAnimationFrame(timestamp -> {
            if (root.parentNode != null) {
                StatsProducer.getStats().update();

                render();
                animate();
            }
        });
    }

    private void render() {
        webGLRenderer.render(scene, camera);
    }
}
