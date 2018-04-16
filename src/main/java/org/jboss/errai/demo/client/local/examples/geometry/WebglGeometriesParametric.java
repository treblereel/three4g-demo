package org.jboss.errai.demo.client.local.examples.geometry;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.inject.Inject;
import elemental2.dom.Document;
import org.jboss.errai.demo.client.api.ParametricGeometries;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.examples.geometry.custom.ParametricGeometriesTorusKnotGeometry;
import org.jboss.errai.demo.client.local.resources.JavascriptTextResource;
import org.jboss.errai.ioc.client.api.LoadAsync;
import org.slf4j.Logger;
import org.treblereel.gwt.three4g.Constants;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.Object3D;
import org.treblereel.gwt.three4g.core.TraverseCallback;
import org.treblereel.gwt.three4g.geometries.ParametricBufferGeometry;
import org.treblereel.gwt.three4g.lights.AmbientLight;
import org.treblereel.gwt.three4g.lights.PointLight;
import org.treblereel.gwt.three4g.loaders.TextureLoader;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterialParameters;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.renderers.WebGLRendererParameters;
import org.treblereel.gwt.three4g.scenes.Scene;
import org.treblereel.gwt.three4g.textures.Texture;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.Date;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 4/11/18.
 */
@LoadAsync
@ApplicationScoped
public class WebglGeometriesParametric extends Attachable {

    float fov = 45f;
    float near = 1f;
    float far = 2000f;
    float y = 400f;

    float ox = 200f;
    float oy = 0f;
    float oz = 200f;

    float multiplyScalar = 30f;

    @Inject
    Logger logger;

    @PostConstruct
    public void init() {

        loadJavaScript(JavascriptTextResource.IMPL.getParametricGeometries().getText());
        loadJavaScript(JavascriptTextResource.IMPL.getCurveExtras().getText());


        camera = new PerspectiveCamera(fov, aspect, near, far);
        camera.position.y = y;
        scene = new Scene();
        //
        AmbientLight ambientLight = new AmbientLight(0xcccccc, 0.4f);
        scene.add(ambientLight);
        PointLight pointLight = new PointLight(0xffffff, 0.8f);
        camera.add(pointLight);


        scene.add(camera);
        //
        Texture map = new TextureLoader().load("img/UV_Grid_Sm.jpg");
        map.wrapS = map.wrapT = Constants.RepeatWrapping;
        map.anisotropy = 16;

        MeshPhongMaterialParameters meshPhongMaterialParameters = new MeshPhongMaterialParameters();
        meshPhongMaterialParameters.map = map;
        meshPhongMaterialParameters.side = Constants.DoubleSide;

        MeshPhongMaterial material = new MeshPhongMaterial(meshPhongMaterialParameters);
        //
        ParametricBufferGeometry geometry;
        Mesh object;



        geometry = new ParametricBufferGeometry(ParametricGeometries.plane(100, 100), 10, 10);
        geometry.center();

        object = new Mesh(geometry, material);
        object.position.set(-200f, 0f, 200f);
        scene.add(object);

        geometry = new ParametricBufferGeometry(ParametricGeometries.klein, 20, 20);
        object = new Mesh(geometry, material);
        object.position.set(0, 0, 200f);
        object.scale.multiplyScalar(5);
        scene.add(object);

        geometry = new ParametricBufferGeometry(ParametricGeometries.mobius, 20, 20);
        object = new Mesh(geometry, material);
        object.position.set(ox, oy, oz);
        object.scale.multiplyScalar(multiplyScalar);
        scene.add(object);


/*        new GUI()

                .add("near", near).onChange(e -> {
            camera.near = ((Double) e).floatValue();

        }).done().add("fov", fov).onChange(e -> {
            camera.fov = ((Double) e).floatValue();
        }).done().add("far", far).onChange(e -> {
            camera.far = ((Double)e).floatValue();
        })
                .done()
                .add("camera.position", y).onChange(e -> {
            camera.position.y =((Double) e).floatValue();
        }).done()
                .add("ox", ox).onChange(e -> {
            object.position.x = ((Double) e).floatValue();
        }).done()
                .add("oy", oy).onChange(e -> {
            object.position.y = ((Double) e).floatValue();
        }).done()
                .add("oz", oz).onChange(e -> {
            object.position.z = ((Double) e).floatValue();
        }).done()


                .finishAndBuild();*/



        org.jboss.errai.demo.client.local.examples.geometry.custom.GrannyKnot grannyKnot = new org.jboss.errai.demo.client.local.examples.geometry.custom.GrannyKnot();
        ParametricGeometriesTorusKnotGeometry torus = new ParametricGeometriesTorusKnotGeometry(50, 10, 50, 20, 2, 3);
        org.jboss.errai.demo.client.local.examples.geometry.custom.SphereGeometry sphere = new org.jboss.errai.demo.client.local.examples.geometry.custom.SphereGeometry(50, 20, 10);
        org.jboss.errai.demo.client.local.examples.geometry.custom.TubeGeometry tube = new org.jboss.errai.demo.client.local.examples.geometry.custom.TubeGeometry(grannyKnot, 100, 3, 8, true, false);

        object = new Mesh(torus, material);
        object.position.set(-200, 0, -200);
        scene.add(object);

        object = new Mesh(sphere, material);
        object.position.set(0, 0, -200);
        scene.add(object);

        object = new Mesh(tube, material);
        object.position.set(200, 0, -200);
        object.scale.multiplyScalar(2);
        scene.add(object);


        //
        WebGLRendererParameters webGLRendererParameters = new WebGLRendererParameters();
        webGLRendererParameters.antialias = true;
        webGLRenderer = new WebGLRenderer(webGLRendererParameters);
        setupWebGLRenderer(webGLRenderer);

    }

    private void render() {
        double timer = new Date().getTime() * 0.0001;
        camera.position.x = (float) (Math.cos(timer) * 800);
        camera.position.z = (float) (Math.sin(timer) * 800);
        camera.lookAt(scene.position);

        scene.traverse(new TraverseCallback() {
            @Override
            public void onEvent(Object3D object) {
                if (object instanceof Mesh) {
                    object.rotation.x = (float) (timer * 5);
                    object.rotation.y = (float) (timer * 2.5);
                }
            }
        });

        webGLRenderer.render(scene, camera);
    }

    private void animate() {
        render();
        AnimationScheduler.get().requestAnimationFrame(timestamp -> {
            if (webGLRenderer.domElement.parentNode != null) {
                animate();
            }
        });
    }

    @Override
    public void doAttachScene() {
        document.body.appendChild(webGLRenderer.domElement);
        onWindowResize();
        animate();
    }

    @Override
    protected void doAttachInfo() {
        info.show().setHrefToInfo("http://threejs.org").setTextContentToInfo("three.js").setInnetHtml(" webgl - parametric geometries");
    }
}
