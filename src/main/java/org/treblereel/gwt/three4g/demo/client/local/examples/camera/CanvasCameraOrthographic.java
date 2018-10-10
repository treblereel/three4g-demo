package org.treblereel.gwt.three4g.demo.client.local.examples.camera;

import elemental2.dom.DomGlobal;
import org.treblereel.gwt.three4g.InjectJavaScriptFor;
import org.treblereel.gwt.three4g.cameras.OrthographicCamera;
import org.treblereel.gwt.three4g.demo.client.local.AppSetup;
import org.treblereel.gwt.three4g.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.demo.client.local.utils.StatsProducer;
import org.treblereel.gwt.three4g.extensions.renderers.CanvasRenderer;
import org.treblereel.gwt.three4g.geometries.BoxGeometry;
import org.treblereel.gwt.three4g.helpers.GridHelper;
import org.treblereel.gwt.three4g.lights.AmbientLight;
import org.treblereel.gwt.three4g.lights.DirectionalLight;
import org.treblereel.gwt.three4g.materials.MeshLambertMaterial;
import org.treblereel.gwt.three4g.materials.parameters.MeshLambertMaterialParameters;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.scenes.Scene;

import java.util.Date;
import java.util.Random;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel on 6/27/18.
 */
@InjectJavaScriptFor(elements = CanvasRenderer.class)
public class CanvasCameraOrthographic extends Attachable {

    public static final String name = "camera / orthographic";
    private OrthographicCamera orthographicCamera;
    private int frustumSize = 600;
    private Random random = new Random();
    private CanvasRenderer canvasRenderer;

    public CanvasCameraOrthographic() {

        orthographicCamera = new OrthographicCamera(frustumSize * aspect / -2, frustumSize * aspect / 2, (float) (frustumSize / 2), (float) (frustumSize / -2), 1, 2000);
        orthographicCamera.position.y = 400;

        scene = new Scene();
        scene.background = new Color(0xf0f0f0);

        // Grid
        GridHelper gridHelper = new GridHelper(1000, 20);
        scene.add(gridHelper);

        // Cubes
        BoxGeometry geometry = new BoxGeometry(50, 50, 50);
        MeshLambertMaterialParameters parameters = new MeshLambertMaterialParameters();
        parameters.color = new Color(0xffffff);
        parameters.overdraw = 0.5f;
        MeshLambertMaterial material = new MeshLambertMaterial(parameters);

        for (int i = 0; i < 200; i++) {

            Mesh cube = new Mesh(geometry, material);

            cube.scale.y = (float) Math.floor(Math.random() * 2 + 1);
            cube.position.x = (float) Math.floor((Math.random() * 1000 - 500) / 50) * 50 + 25;
            cube.position.y = (cube.scale.y * 50) / 2;
            cube.position.z = (float) Math.floor((Math.random() * 1000 - 500) / 50) * 50 + 25;

            scene.add(cube);

        }
        // Lights
        AmbientLight ambientLight = new AmbientLight(random.nextInt() * 0x10);
        scene.add(ambientLight);

        DirectionalLight directionalLight = new DirectionalLight(random.nextInt() * 0xffffff);
        directionalLight.position.x = (float) (Math.random() - 0.5);
        directionalLight.position.y = (float) (Math.random() - 0.5);
        directionalLight.position.z = (float) (Math.random() - 0.5);
        directionalLight.position.normalize();
        scene.add(directionalLight);

        DirectionalLight directionalLight1 = new DirectionalLight(random.nextInt() * 0xffffff);
        directionalLight1.position.x = (float) (Math.random() - 0.5);
        directionalLight1.position.y = (float) (Math.random() - 0.5);
        directionalLight1.position.z = (float) (Math.random() - 0.5);
        directionalLight1.position.normalize();
        scene.add(directionalLight1);

        canvasRenderer = new CanvasRenderer();
        //renderer.setPixelRatio(devicePixelRatio);
        canvasRenderer.setSize(window.innerWidth, window.innerHeight);
    }

    @Override
    public void onWindowResize() {
        orthographicCamera.left = -frustumSize * aspect / 2;
        orthographicCamera.right = frustumSize * aspect / 2;
        orthographicCamera.top = frustumSize / 2;
        orthographicCamera.bottom = -frustumSize / 2;
        orthographicCamera.updateProjectionMatrix();
        canvasRenderer.setSize(getWidth(), getHeight());
    }

    public void doAttachScene() {
        root.appendChild(canvasRenderer.domElement);
        onWindowResize();
        animate();
    }

    @Override
    protected void doAttachInfo() {
        AppSetup.infoDiv.show().setHrefToInfo("http://threejs.org").setTextContentToInfo("threejs - orthographic view").setInnetHtml("");

    }

    private void animate() {
        StatsProducer.getStats().update();
        DomGlobal.requestAnimationFrame(timestamp -> {
            if (root.parentNode != null) {
                render();
                animate();
            }
        });
    }

    private void render() {
        double timer = new Date().getTime() * 0.0001;
        orthographicCamera.position.x = (float) (Math.cos(timer) * 800);
        orthographicCamera.position.z = (float) (Math.sin(timer) * 800);
        orthographicCamera.lookAt(scene.position);
        canvasRenderer.render(scene, orthographicCamera);
    }
}
