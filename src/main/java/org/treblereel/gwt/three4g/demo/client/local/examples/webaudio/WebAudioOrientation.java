package org.treblereel.gwt.three4g.demo.client.local.examples.webaudio;

import com.google.gwt.animation.client.AnimationScheduler;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLAudioElement;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLParagraphElement;
import elemental2.dom.HTMLSourceElement;
import org.treblereel.gwt.three4g.InjectJavaScriptFor;
import org.treblereel.gwt.three4g.THREE;
import org.treblereel.gwt.three4g.audio.AudioListener;
import org.treblereel.gwt.three4g.audio.PositionalAudio;
import org.treblereel.gwt.three4g.cameras.OrthographicCamera;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.JsObject;
import org.treblereel.gwt.three4g.demo.client.local.AppSetup;
import org.treblereel.gwt.three4g.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.extensions.controls.OrbitControls;
import org.treblereel.gwt.three4g.extensions.loaders.GLTFLoader;
import org.treblereel.gwt.three4g.geometries.BoxBufferGeometry;
import org.treblereel.gwt.three4g.geometries.PlaneBufferGeometry;
import org.treblereel.gwt.three4g.helpers.GridHelper;
import org.treblereel.gwt.three4g.lights.DirectionalLight;
import org.treblereel.gwt.three4g.lights.HemisphereLight;
import org.treblereel.gwt.three4g.loaders.CubeTextureLoader;
import org.treblereel.gwt.three4g.loaders.OnLoadCallback;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.materials.MeshStandardMaterial;
import org.treblereel.gwt.three4g.materials.parameters.MeshPhongMaterialParameters;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.renderers.parameters.WebGLRendererParameters;
import org.treblereel.gwt.three4g.scenes.Fog;
import org.treblereel.gwt.three4g.scenes.Scene;
import org.treblereel.gwt.three4g.textures.CubeTexture;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 7/10/18.
 */
@InjectJavaScriptFor(elements = {GLTFLoader.class, OrbitControls.class})
public class WebAudioOrientation extends Attachable {

    public static final String name = "webaudio / orientation";

    private HTMLAudioElement audio;
    private HTMLDivElement overlay;

    public WebAudioOrientation() {
        audio = (HTMLAudioElement) DomGlobal.document.createElement("audio");
        audio.id = "music";
        audio.loop = true;
        audio.style.display = "none";
        audio.setAttribute("preload", "auto");
        container.appendChild(audio);

        HTMLSourceElement sourceElement1 = (HTMLSourceElement) DomGlobal.document.createElement("source");
        sourceElement1.src = "sounds/376737_Skullbeatz___Bad_Cat_Maste.ogg";
        sourceElement1.type = "audio/ogg";
        audio.appendChild(sourceElement1);

        HTMLSourceElement sourceElement2 = (HTMLSourceElement) DomGlobal.document.createElement("source");
        sourceElement2.src = "sounds/376737_Skullbeatz___Bad_Cat_Maste.mp3";
        sourceElement2.type = "audio/mpeg";
        audio.appendChild(sourceElement2);

        overlay = (HTMLDivElement) DomGlobal.document.createElement("div");
        overlay.id = "a_overlay";
        container.appendChild(overlay);

        HTMLDivElement div = (HTMLDivElement) DomGlobal.document.createElement("div");
        overlay.appendChild(div);

        HTMLButtonElement button = (HTMLButtonElement) DomGlobal.document.createElement("button");
        button.id = "startButton";
        button.textContent = "Click to Play";
        div.appendChild(button);

        HTMLParagraphElement p = (HTMLParagraphElement) DomGlobal.document.createElement("p");
        p.textContent = "Automatic audio playback requires a user interaction.";
        div.appendChild(p);

        button.addEventListener("click", evt -> init());
    }


    public void init() {
        overlay.remove();
        camera = new PerspectiveCamera(45, aspect, 0.1f, 100);

        camera.position.set(0, 1, 2);
        String[] textures = {"px.jpg", "nx.jpg", "py.jpg", "ny.jpg", "pz.jpg", "nz.jpg"};


        CubeTexture reflectionCube = new CubeTextureLoader()
                .setPath("textures/cube/SwedishRoyalCastle/")
                .load(textures);
        reflectionCube.format = THREE.RGBFormat;
        scene = new Scene();
        scene.background = new Color(0xa0a0a0);
        scene.fog = new Fog(0xa0a0a0, 2, 20);
        //
        HemisphereLight hemisphereLight = new HemisphereLight(0xffffff, 0x444444);
        hemisphereLight.position.set(0, 200, 0);
        scene.add(hemisphereLight);

        DirectionalLight light = new DirectionalLight(0xffffff);
        light.position.set(0, 200, 100);
        light.castShadow = true;
        ((OrthographicCamera) light.shadow.camera).top = 180;
        ((OrthographicCamera) light.shadow.camera).bottom = -100;
        ((OrthographicCamera) light.shadow.camera).left = -120;
        ((OrthographicCamera) light.shadow.camera).right = 120;
        scene.add(light);
        //
        MeshPhongMaterialParameters parameters = new MeshPhongMaterialParameters();
        parameters.color = new Color(0x999999);
        parameters.depthWrite = false;

        Mesh mesh = new Mesh(new PlaneBufferGeometry(20, 20), new MeshPhongMaterial(parameters));
        mesh.rotation.x = (float) -Math.PI / 2;
        mesh.receiveShadow = true;
        scene.add(mesh);
        GridHelper grid = new GridHelper(20, 20, new Color(0x000000), new Color(0x000000));
        grid.material.opacity = 0.2f;
        grid.material.transparent = true;
        scene.add(grid);
        //
        AudioListener listener = new AudioListener();
        camera.add(listener);

        PositionalAudio positionalAudio = new PositionalAudio(listener);
        positionalAudio.setMediaElementSource(audio);
        positionalAudio.setRefDistance(1);
        positionalAudio.setDirectionalCone(210, 230, 0.1f);
        //
        GLTFLoader gltfLoader = new GLTFLoader();
        gltfLoader.load("models/gltf/BoomBox/glTF-Binary/BoomBox.glb", new OnLoadCallback<JsObject>() {
            @Override
            public void onLoad(JsObject gltf) {

                Scene boomBox = gltf.getProperty("scene");
                boomBox.position.set(0, 0.2f, 0);
                boomBox.scale.set(20, 20, 20);
                boomBox.traverse(object -> {
                    if (object instanceof Mesh) {
                        Mesh mesh1 = object.cast();
                        MeshStandardMaterial material = mesh1.material.cast();
                        material.envMap = reflectionCube;
                        mesh1.geometry.rotateY((float) -Math.PI);
                    }
                });

                audio.play();
                boomBox.add(positionalAudio);
                scene.add(boomBox);
                animate();

            }
        });


        // sound is damped behind this wall
        BoxBufferGeometry wallGeometry = new BoxBufferGeometry(2, 1, 0.1f);
        MeshBasicMaterial wallMaterial = new MeshBasicMaterial();
        wallMaterial.color = new Color(0xff0000);
        wallMaterial.wireframe = true;
        Mesh wall = new Mesh(wallGeometry, wallMaterial);
        wall.position.set(0, 0.5f, -0.5f);
        scene.add(wall);
        //
        WebGLRendererParameters parameters1 = new WebGLRendererParameters();
        parameters1.antialias = true;
        renderer = new WebGLRenderer(parameters1);
        setupWebGLRenderer(renderer);
        container.appendChild(renderer.domElement);
        renderer.gammaOutput = true;
        //
        orbitControls = new OrbitControls(camera, renderer.domElement);
        orbitControls.target.set(0, 0.1f, 0);
        orbitControls.update();
        orbitControls.minDistance = 0.5f;
        orbitControls.maxDistance = 10;
        orbitControls.maxPolarAngle = 0.5f * (float) Math.PI;

        root.appendChild(renderer.domElement);
        animate();
    }

    @Override
    public void detach() {
        super.doDetach();
        container.removeChild(audio);


    }

    public void doAttachScene() {
        root.appendChild(container);
        onWindowResize();
    }

    @Override
    protected void doAttachInfo() {
        AppSetup.infoDiv.show().setHrefToInfo("http://threejs.org").setInnetHtml("    webaudio orientation | music by <a href=\"http://www.newgrounds.com/audio/listen/376737\" target=\"_blank\" rel=\"noopener noreferrer\">skullbeatz</a>");
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

    private void render() {
        renderer.render(scene, camera);
    }
}


