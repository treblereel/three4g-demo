package org.jboss.errai.demo.client.local.examples.animation;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.ScriptInjector;
import com.google.inject.Inject;
import org.jboss.errai.demo.client.local.Attachable;
import org.slf4j.Logger;
import org.treblereel.gwt.three4g.geometries.ParametricGeometry;
import org.treblereel.gwt.three4g.lights.DirectionalLight;
import org.treblereel.gwt.three4g.materials.Material;
import org.treblereel.gwt.three4g.objects.Mesh;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.Date;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/22/18.
 */
@ApplicationScoped
public class WebglAnimationCloth extends Attachable {


    DirectionalLight light;
    ParametricGeometry clothGeometry;
    Material materials;
    Mesh sphere;


    @PostConstruct
    public void init() {

        int[][] pinsFormation = new int[5][];
        int[] pins = new int[]{6};
        pinsFormation[0] = pins;
        pins = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        pinsFormation[1] = pins;
        pins = new int[]{0};
        pinsFormation[2] = pins;
        pins = new int[]{};
        pinsFormation[3] = pins;
        //   pins = [ 0, cloth.w ];
        pinsFormation[4] = pins;
        pins = pinsFormation[1];


        ScriptInjector.fromUrl("js/Cloth.js")
        .setWindow(ScriptInjector.TOP_WINDOW)
                .setCallback(new Callback<Void, Exception>() {
                    @Override
                    public void onFailure(Exception reason) {
                        logger.warn(" onFailure " + reason.getMessage() );
                    }

                    @Override
                    public void onSuccess(Void result) {
                        logger.warn(" onSuccess ");
                        logger.warn(" onSuccess 2 " + new Cloth().ballSize);
                        logger.warn(" onSuccess 2 " + new Cloth().plane(1,2).isVector3);



                    }
                }).inject();


        logger.warn(" done ");





/*

        // scene
        scene = new Scene();
        scene.background = new Color(0xcce0ff);
        scene.fog = new Fog(0xcce0ff, 500, 10000);
        // camera
        camera = new PerspectiveCamera(30, aspect, 1, 10000);
        camera.position.set(1000, 50, 1500);
        // lights
        scene.add(new AmbientLight(0x666666));
        light = new DirectionalLight(0xdfebff, 1);
        light.position.set(50, 200, 100);
        light.position.multiplyScalar(1.3f);
        light.castShadow = true;
        light.shadow.mapSize.width = 1024;
        light.shadow.mapSize.height = 1024;
        int d = 300;
        ((OrthographicCamera) light.shadow.camera).left = -d;
        ((OrthographicCamera) light.shadow.camera).right = d;
        ((OrthographicCamera) light.shadow.camera).top = d;
        ((OrthographicCamera) light.shadow.camera).bottom = -d;
        ((OrthographicCamera) light.shadow.camera).far = 1000;
        scene.add(light);
        // cloth material
        TextureLoader loader = new TextureLoader();
        Texture clothTexture = loader.load("img/circuit_pattern.png");
        clothTexture.anisotropy = 16;

        MeshLambertMaterialParameters meshLambertMaterialParameters = new MeshLambertMaterialParameters();
        meshLambertMaterialParameters.map = clothTexture;
        meshLambertMaterialParameters.side = DoubleSide;
        meshLambertMaterialParameters.alphaTest = 0.5f;

        MeshLambertMaterial clothMaterial = new MeshLambertMaterial(meshLambertMaterialParameters);
        // cloth
        // geometry


        clothGeometry = new ParametricGeometry(new ParametricGeometryFunction() {
            @Override
            public Vector3 call(float u, float v) {
                return null;
            }
        }, 10, 10);
        // cloth mesh
        Mesh object = new Mesh(clothGeometry, clothMaterial);
        object.position.set(0, 0, 0);
        object.castShadow = true;
        scene.add(object);

        MeshDepthMaterialParameters meshDepthMaterialParameters = new MeshDepthMaterialParameters();
        meshDepthMaterialParameters.depthPacking = Constants.RGBADepthPacking;
        meshDepthMaterialParameters.map = clothTexture;
        meshDepthMaterialParameters.alphaTest = 0.5f;

        object.customDepthMaterial = new MeshDepthMaterial(meshDepthMaterialParameters);
        // sphere
        SphereBufferGeometry ballGeo = new SphereBufferGeometry(60, 32, 16);
        MeshLambertMaterial ballMaterial = new MeshLambertMaterial();
        sphere = new Mesh(ballGeo, ballMaterial);
        sphere.castShadow = true;
        sphere.receiveShadow = true;
        scene.add(sphere);
        // ground
        Texture groundTexture = loader.load("img/grasslight-big.jpg");
        groundTexture.wrapS = groundTexture.wrapT = Constants.RepeatWrapping;
        groundTexture.repeat.set(25, 25);
        groundTexture.anisotropy = 16;

        meshLambertMaterialParameters = new MeshLambertMaterialParameters();
        meshLambertMaterialParameters.map = groundTexture;
        Material groundMaterial = new MeshLambertMaterial(meshLambertMaterialParameters);
        Mesh mesh = new Mesh(new PlaneBufferGeometry(20000, 20000), groundMaterial);
        mesh.position.y = -250;
        mesh.rotation.x = (float) -Math.PI / 2;
        mesh.receiveShadow = true;
        scene.add(mesh);
        // poles
        BoxBufferGeometry poleGeo = new BoxBufferGeometry(5, 375, 5);
        MeshLambertMaterial poleMat = new MeshLambertMaterial();
        mesh = new Mesh(poleGeo, poleMat);
        mesh.position.x = -125;
        mesh.position.y = -62;
        mesh.receiveShadow = true;
        mesh.castShadow = true;

        scene.add(mesh);
        mesh = new Mesh(poleGeo, poleMat);
        mesh.position.x = 125;
        mesh.position.y = -62;
        mesh.receiveShadow = true;
        mesh.castShadow = true;
        scene.add(mesh);

        mesh = new Mesh(new BoxBufferGeometry(255f, 5f, 5f), poleMat);
        mesh.position.y = -250 + (750 / 2);
        mesh.position.x = 0;
        mesh.receiveShadow = true;
        mesh.castShadow = true;
        scene.add(mesh);

        BoxBufferGeometry gg = new BoxBufferGeometry(10, 10, 10);
        mesh = new Mesh(gg, poleMat);
        mesh.position.y = -250;
        mesh.position.x = 125;
        mesh.receiveShadow = true;
        mesh.castShadow = true;
        scene.add(mesh);

        mesh = new Mesh(gg, poleMat);
        mesh.position.y = -250;
        mesh.position.x = -125;
        mesh.receiveShadow = true;
        mesh.castShadow = true;
        scene.add(mesh);
        // renderer
        WebGLRendererParameters webGLRendererParameters = new WebGLRendererParameters();
        webGLRendererParameters.antialias = true;
        webGLRenderer = new WebGLRenderer(webGLRendererParameters);
        //webGLRenderer.setPixelRatio( window.devicePixelRatio );
        setupWebGLRenderer(webGLRenderer);

        webGLRenderer.gammaInput = true;
        webGLRenderer.gammaOutput = true;
        webGLRenderer.shadowMap.enabled = true;
        // controls
        OrbitControls controls = new OrbitControls(camera, webGLRenderer.domElement);
        controls.maxPolarAngle = Math.PI * 0.5;
        controls.minDistance = 1000;
        controls.maxDistance = 5000;
        // performance monitor
        //stats = new Stats();
        //container.appendChild( stats.dom );
        //
        sphere.visible = !true;
*/


    }


    @Override
    protected void doAttachScene() {
        //document.body.appendChild(webGLRenderer.domElement);
        //onWindowResize();
        //animate();
    }

    @Override
    protected void doAttachInfo() {

    }

    @Inject
    Logger logger;

    private void animate() {
        AnimationScheduler.get().requestAnimationFrame(timestamp -> {
            if (webGLRenderer.domElement != null) {
                double time = new Date().getTime();
                double windStrength = Math.cos(time / 7000) * 20 + 40;
/*
                Cloth.windForce.set((float) Math.sin(time / 2000), (float) Math.cos(time / 3000), (float) Math.sin(time / 1000));
                Cloth.windForce.normalize();
                Cloth.windForce.multiplyScalar((float) windStrength);
                Cloth.simulate(time);*/

                render();
                animate();
            }
        });
    }

    private void render() {
       /* Particle[] p = Cloth.cloth.particles;
        for (int i = 0, il = p.length; i < il; i++) {
            clothGeometry.vertices[i].copy(p[i].position);
        }
        clothGeometry.verticesNeedUpdate = true;
        clothGeometry.computeFaceNormals();
        clothGeometry.computeVertexNormals(true);
        sphere.position.copy(Cloth.ballPosition);*/

        webGLRenderer.render(scene, camera);
    }
}
