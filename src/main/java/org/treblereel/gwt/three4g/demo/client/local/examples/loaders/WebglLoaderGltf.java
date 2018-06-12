package org.treblereel.gwt.three4g.demo.client.local.examples.loaders;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.core.client.GWT;
import org.treblereel.gwt.three4g.animation.AnimationClip;
import org.treblereel.gwt.three4g.animation.AnimationMixer;
import org.treblereel.gwt.three4g.cameras.OrthographicCamera;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.Clock;
import org.treblereel.gwt.three4g.core.JsObject;
import org.treblereel.gwt.three4g.core.Object3D;
import org.treblereel.gwt.three4g.core.TraverseCallback;
import org.treblereel.gwt.three4g.demo.client.local.AppSetup;
import org.treblereel.gwt.three4g.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.demo.client.local.utils.JSON;
import org.treblereel.gwt.three4g.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.examples.controls.OrbitControls;
import org.treblereel.gwt.three4g.examples.loaders.FBXLoader;
import org.treblereel.gwt.three4g.examples.loaders.GLTFLoader;
import org.treblereel.gwt.three4g.geometries.PlaneBufferGeometry;
import org.treblereel.gwt.three4g.helpers.GridHelper;
import org.treblereel.gwt.three4g.lights.DirectionalLight;
import org.treblereel.gwt.three4g.lights.HemisphereLight;
import org.treblereel.gwt.three4g.loaders.CubeTextureLoader;
import org.treblereel.gwt.three4g.loaders.OnLoadCallback;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.materials.MeshStandardMaterial;
import org.treblereel.gwt.three4g.materials.parameters.MeshPhongMaterialParameters;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.objects.Group;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.renderers.parameters.WebGLRendererParameters;
import org.treblereel.gwt.three4g.scenes.Fog;
import org.treblereel.gwt.three4g.scenes.Scene;
import org.treblereel.gwt.three4g.textures.CubeTexture;
import org.treblereel.gwt.three4g.textures.Texture;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/10/18.
 */
public class WebglLoaderGltf extends Attachable {

    public static final String name = "loader / gltf";

    private OrbitControls controls;
    private CubeTexture envMap;

    public WebglLoaderGltf() {


        camera = new PerspectiveCamera( 45, window.innerWidth / window.innerHeight, 0.25f, 20 );
        camera.position.set( -1.8f, 0.9f, 2.7f );
        controls = new OrbitControls( camera );
        controls.target.set( 0f, -0.2f, -0.2f );
        controls.update();

        // envmap
        String path = "textures/cube/Bridge2/";
        String format = ".jpg";

        String[] urls = new String[6];
        urls[0] = path + "posx" + format;
        urls[1] = path + "negx" + format;
        urls[2] = path + "posy" + format;
        urls[3] = path + "negy" + format;
        urls[4] = path + "posz" + format;
        urls[5] = path + "negz" + format;

        envMap = new CubeTextureLoader().load(urls);

        scene = new Scene();
        scene.background = envMap;

        HemisphereLight light = new HemisphereLight( 0xbbbbff, 0x444422 );
        light.position.set( 0, 1, 0 );
        scene.add( light );
        // model
        GLTFLoader loader = new GLTFLoader();
        loader.load("models/gltf/DamagedHelmet/glTF/DamagedHelmet.gltf", new OnLoadCallback<JsObject>() {
                    @Override
                    public void onLoad(JsObject object) {
                        Scene obj = object.getProperty("scene");

                        obj.traverse(new TraverseCallback() {
                            @Override
                            public void onEvent(Object3D child) {
                                if ( child instanceof Mesh ) {
                                    MeshStandardMaterial material = child.getProperty("material");
                                    material.envMap = envMap;
                                }
                            }
                        } );
                        scene.add( obj );
                    }
                });

        WebGLRendererParameters webGLRendererParameters = new WebGLRendererParameters();
        webGLRendererParameters.antialias = true;

        webGLRenderer = new WebGLRenderer(webGLRendererParameters);
        webGLRenderer.setSize(window.innerWidth, window.innerHeight);
        webGLRenderer.gammaInput = true;
        //
        container.appendChild(webGLRenderer.domElement);

    }

    @Override
    protected void doAttachScene() {
        root.appendChild(container);
        webGLRenderer.setSize(getWidth(), getHeight());
        animate();
    }

    @Override
    protected void doAttachInfo() {
        AppSetup.infoDiv.show().setHrefToInfo("http://threejs.org").setTextContentToInfo("three.js").setInnetHtml(" - GLTFLoader<br />\n" +
                "\t\t\tBattle Damaged Sci-fi Helmet by\n" +
                "\t\t\t<a href=\"https://sketchfab.com/theblueturtle_\" target=\"_blank\" rel=\"noopener\">theblueturtle_</a><br />");


    }

    private void animate() {
        AnimationScheduler.get().requestAnimationFrame(timestamp -> {
            if (root.parentNode != null) {
                StatsProducer.getStats().update();
                webGLRenderer.render(scene, camera);
                animate();

            }
        });
    }

}
