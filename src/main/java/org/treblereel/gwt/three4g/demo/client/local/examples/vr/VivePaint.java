package org.treblereel.gwt.three4g.demo.client.local.examples.vr;

import com.google.gwt.core.client.ScriptInjector;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import jsinterop.base.Js;
import org.treblereel.gwt.three4g.demo.client.api.PaintViveController;
import org.treblereel.gwt.three4g.demo.client.local.AppSetup;
import org.treblereel.gwt.three4g.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.demo.client.local.resources.JavascriptTextResource;
import org.treblereel.gwt.three4g.THREE;
import org.treblereel.gwt.three4g.cameras.OrthographicCamera;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.BufferAttribute;
import org.treblereel.gwt.three4g.core.BufferGeometry;
import org.treblereel.gwt.three4g.core.Object3D;
import org.treblereel.gwt.three4g.core.bufferattributes.Float32BufferAttribute;
import org.treblereel.gwt.three4g.demo.client.local.Attachable;
import org.treblereel.gwt.three4g.examples.loaders.OBJLoader;
import org.treblereel.gwt.three4g.examples.vr.WebVR;
import org.treblereel.gwt.three4g.geometries.BoxBufferGeometry;
import org.treblereel.gwt.three4g.geometries.IcosahedronBufferGeometry;
import org.treblereel.gwt.three4g.geometries.PlaneBufferGeometry;
import org.treblereel.gwt.three4g.helpers.GridHelper;
import org.treblereel.gwt.three4g.lights.DirectionalLight;
import org.treblereel.gwt.three4g.lights.HemisphereLight;
import org.treblereel.gwt.three4g.loaders.OnLoadCallback;
import org.treblereel.gwt.three4g.loaders.TextureLoader;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.materials.MeshPhongMaterial;
import org.treblereel.gwt.three4g.materials.MeshStandardMaterial;
import org.treblereel.gwt.three4g.math.Color;
import org.treblereel.gwt.three4g.math.Matrix4;
import org.treblereel.gwt.three4g.math.Vector3;
import org.treblereel.gwt.three4g.objects.Group;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.renderers.parameters.WebGLRendererParameters;
import org.treblereel.gwt.three4g.scenes.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel on 6/5/18.
 */
public class VivePaint extends Attachable {

    public static final String name = "vive / paint";

    private HTMLDivElement container = (HTMLDivElement) DomGlobal.document.createElement("div");
    private PaintViveController controller1, controller2;
    private boolean ready = false;

    private Mesh line;
    Vector3 up = new Vector3(0, 1, 0);

    Vector3 vector1 = new Vector3();
    Vector3 vector2 = new Vector3();
    Vector3 vector3 = new Vector3();
    Vector3 vector4 = new Vector3();

    public VivePaint() {

        ScriptInjector.fromString(JavascriptTextResource.IMPL.getPaintViveController().getText())
                .setWindow(ScriptInjector.TOP_WINDOW).inject();

        scene = new Scene();
        scene.background = new Color(0x222222);
        camera = new PerspectiveCamera(70, window.innerWidth / window.innerHeight, 0.1f, 10);

        BoxBufferGeometry geometry = new BoxBufferGeometry(0.5f, 0.8f, 0.5f);
        MeshStandardMaterial material = new MeshStandardMaterial();
        material.color = new Color(0x444444);
        material.roughness = 1.0f;
        material.metalness = 0.0f;

        Mesh table = new Mesh(geometry, material);
        table.position.y = 0.35f;
        table.position.z = 0.85f;
        table.castShadow = true;
        table.receiveShadow = true;
        scene.add(table);


        PlaneBufferGeometry planeBufferGeometry = new PlaneBufferGeometry(4, 4);
        MeshStandardMaterial meshStandardMaterial = new MeshStandardMaterial();
        meshStandardMaterial.color = new Color(0x222222);
        meshStandardMaterial.roughness = 1.0f;
        meshStandardMaterial.metalness = 0.0f;

        Mesh floor = new Mesh(planeBufferGeometry, meshStandardMaterial);
        floor.rotation.x = (float) -Math.PI / 2;
        floor.receiveShadow = true;
        scene.add(floor);
        scene.add(new GridHelper(20, 40, new Color(0x111111), new Color(0x111111)));
        scene.add(new HemisphereLight(0x888877, 0x777788));
        DirectionalLight light = new DirectionalLight(0xffffff);
        light.position.set(0, 6, 0);
        light.castShadow = true;
        ((OrthographicCamera) light.shadow.camera).top = 2;
        ((OrthographicCamera) light.shadow.camera).bottom = -2;
        ((OrthographicCamera) light.shadow.camera).right = 2;
        ((OrthographicCamera) light.shadow.camera).left = -2;
        light.shadow.mapSize.set(4096f, 4096f);
        scene.add(light);

        WebGLRendererParameters parameters = new WebGLRendererParameters();
        parameters.alpha = true;
        parameters.antialias = true;

        webGLRenderer = new WebGLRenderer(parameters);
        webGLRenderer.setSize(window.innerWidth, window.innerHeight);

        webGLRenderer.gammaInput = true;
        webGLRenderer.gammaOutput = true;
        webGLRenderer.shadowMap.enabled = true;
        webGLRenderer.vr.enabled = true;

        container.appendChild(webGLRenderer.domElement);
        container.appendChild(WebVR.createButton(webGLRenderer));


        controller1 = new PaintViveController(0);
        controller1.standingMatrix = webGLRenderer.vr.getStandingMatrix();

        Vector3[] vectors = new Vector3[2];
        vectors[0] = new Vector3();
        vectors[1] = new Vector3();

        controller1.userData.setProperty("points", vectors);

        Matrix4[] matrices = new Matrix4[2];
        matrices[0] = new Matrix4();
        matrices[1] = new Matrix4();

        controller1.userData.setProperty("matrices", matrices);

        scene.add(controller1);
        controller2 = new PaintViveController(1);
        controller2.standingMatrix = webGLRenderer.vr.getStandingMatrix();

        vectors = new Vector3[2];
        vectors[0] = new Vector3();
        vectors[1] = new Vector3();

        controller2.userData.setProperty("points", vectors);

        matrices = new Matrix4[2];
        matrices[0] = new Matrix4();
        matrices[1] = new Matrix4();

        controller2.userData.setProperty("matrices", matrices);

        scene.add(controller2);

        OBJLoader loader = new OBJLoader();

        loader.load("models/obj/vive-controller/vr_controller_vive_1_5.obj", new OnLoadCallback<Object3D>() {
                    @Override
                    public void onLoad(Object3D object) {

                        TextureLoader loader = new TextureLoader();
                        loader.setPath("models/obj/vive-controller/");

                        Object3D controller = object.children[0];

                        MeshPhongMaterial material = Js.uncheckedCast(Js.asPropertyMap(controller).get("material"));

                        material.map = loader.load("onepointfive_texture.png");
                        material.specularMap = loader.load("onepointfive_spec.png");
                        controller.castShadow = true;
                        controller.receiveShadow = true;

                        Mesh pivot = new Mesh(new IcosahedronBufferGeometry(0.01f, 2));
                        pivot.name = "pivot";
                        pivot.position.y = -0.016f;
                        pivot.position.z = -0.043f;
                        pivot.rotation.x = (float) (Math.PI / 5.5);

                        controller.add(pivot);
                        controller1.add(controller.clone());
                        pivot.material = pivot.material.clone();
                        controller2.add(controller.clone());

                        initGeometry();
                        ready = true;
                    }
                }
        );


    }


    public void initGeometry() {
        BufferGeometry geometry = new BufferGeometry();
        BufferAttribute positions = new Float32BufferAttribute(new float[1000000 * 3], 3);
        positions.dynamic = true;
        geometry.addAttribute("position", positions);
        BufferAttribute normals = new Float32BufferAttribute(new float[1000000 * 3], 3);
        normals.dynamic = true;
        geometry.addAttribute("normal", normals);
        BufferAttribute colors = new Float32BufferAttribute(new float[1000000 * 3], 3);
        colors.dynamic = true;
        geometry.addAttribute("color", colors);
        geometry.drawRange.count = 0;

        MeshStandardMaterial material = new MeshStandardMaterial();
        material.roughness = 0.9f;
        material.metalness = 0.0f;
        material.vertexColors = THREE.VertexColors;
        material.side = THREE.DoubleSide;

        line = new Mesh(geometry, material);
        line.frustumCulled = false;
        line.castShadow = true;
        line.receiveShadow = true;
        scene.add(line);
        // Shapes
        //shapes['tube'] = getTubeShapes(1.0f);


    }

    public Vector3[] getTubeShapes(float size) {
        double PI2 = Math.PI * 2;
        int sides = 10;
        List<Vector3> array = new ArrayList<>();
        float radius = 0.01f * size;

        for (int i = 0; i < sides; i++) {
            double angle = (i / sides) * PI2;
            array.add(new Vector3((float) Math.sin(angle) * radius, (float) Math.cos(angle) * radius, 0));
        }
        return (Vector3[]) array.toArray();
    }

    public void stroke(PaintViveController controller, Vector3 point1, Vector3 point2, Matrix4 matrix1, Matrix4 matrix2) {
        Color color = controller.getColor();
        Vector3[] shapes = getTubeShapes(controller.getSize());
        BufferGeometry geometry = (BufferGeometry) line.geometry;

        int count = geometry.drawRange.count;


        //TODO
        float[] positions = Js.uncheckedCast(geometry.getAttribute("position").array);
        float[] normals = Js.uncheckedCast(geometry.getAttribute("normal").array);
        float[] colors = Js.uncheckedCast(geometry.getAttribute("color").array);


        for (int j = 0, jl = shapes.length; j < jl; j++) {
            Vector3 vertex1 = shapes[j];
            Vector3 vertex2 = shapes[(j + 1) % jl];
            // positions
            vector1.copy(vertex1);
            vector1.applyMatrix4(matrix2);
            vector1.add(point2);
            vector2.copy(vertex2);
            vector2.applyMatrix4(matrix2);
            vector2.add(point2);
            vector3.copy(vertex2);
            vector3.applyMatrix4(matrix1);
            vector3.add(point1);
            vector4.copy(vertex1);
            vector4.applyMatrix4(matrix1);
            vector4.add(point1);
            vector1.toArray(positions, (count + 0) * 3);
            vector2.toArray(positions, (count + 1) * 3);
            vector4.toArray(positions, (count + 2) * 3);
            vector2.toArray(positions, (count + 3) * 3);
            vector3.toArray(positions, (count + 4) * 3);
            vector4.toArray(positions, (count + 5) * 3);
            // normals
            vector1.copy(vertex1);
            vector1.applyMatrix4(matrix2);
            vector1.normalize();
            vector2.copy(vertex2);
            vector2.applyMatrix4(matrix2);
            vector2.normalize();
            vector3.copy(vertex2);
            vector3.applyMatrix4(matrix1);
            vector3.normalize();
            vector4.copy(vertex1);
            vector4.applyMatrix4(matrix1);
            vector4.normalize();
            vector1.toArray(normals, (count + 0) * 3);
            vector2.toArray(normals, (count + 1) * 3);
            vector4.toArray(normals, (count + 2) * 3);
            vector2.toArray(normals, (count + 3) * 3);
            vector3.toArray(normals, (count + 4) * 3);
            vector4.toArray(normals, (count + 5) * 3);
            // colors
            color.toArray(colors, (count + 0) * 3);
            color.toArray(colors, (count + 1) * 3);
            color.toArray(colors, (count + 2) * 3);
            color.toArray(colors, (count + 3) * 3);
            color.toArray(colors, (count + 4) * 3);
            color.toArray(colors, (count + 5) * 3);
            count += 6;
        }
        geometry.drawRange.count = count;
    }

    public void updateGeometry(int start, int end) {
        if (start == end) return;

        int offset = start * 3;
        int count = (end - start) * 3;
        BufferGeometry geometry = (BufferGeometry) line.geometry;

        geometry.getAttribute("position").updateRange.offset = offset;
        geometry.getAttribute("position").updateRange.count = count;
        geometry.getAttribute("position").needsUpdate = true;
        geometry.getAttribute("normal").updateRange.offset = offset;
        geometry.getAttribute("normal").updateRange.count = count;
        geometry.getAttribute("normal").needsUpdate = true;
        geometry.getAttribute("color").updateRange.offset = offset;
        geometry.getAttribute("color").updateRange.count = count;
        geometry.getAttribute("color").needsUpdate = true;
    }


    public void handleController(PaintViveController controller) {
        controller.update();
        Mesh pivot = (Mesh) controller.getObjectByName("pivot");
        if (pivot != null) {
            ((MeshBasicMaterial) pivot.material).color.copy(controller.getColor());
            pivot.scale.setScalar(controller.getSize());
            Matrix4 matrix = pivot.matrixWorld;
            Vector3 point1 = ((Vector3[]) controller.userData.getProperty("points"))[0];
            Vector3 point2 = ((Vector3[]) controller.userData.getProperty("points"))[1];
            Matrix4 matrix1 = ((Matrix4[]) controller.userData.getProperty("matrices"))[0];
            Matrix4 matrix2 = ((Matrix4[]) controller.userData.getProperty("matrices"))[1];
            point1.setFromMatrixPosition(matrix);
            matrix1.lookAt(point2, point1, up);
            if (controller.getButtonState("trigger")) {
                stroke(controller, point1, point2, matrix1, matrix2);
            }
            point2.copy(point1);
            matrix2.copy(matrix1);
        }
    }


    @Override
    protected void doAttachScene() {
        root.appendChild(container);
        onWindowResize();
        animate();
    }

    private void animate() {
        webGLRenderer.setAnimationLoop(() -> {
            if (container.parentNode != null && container.parentNode.parentNode != null && ready) {
                render();
            }
        });
    }

    @Override
    protected void doAttachInfo() {
        AppSetup.infoDiv.show().setHrefToInfo("http://threejs.org").setTextContentToInfo("three.js").setInnetHtml(" <a href=\"http://threejs.org\" target=\"_blank\" rel=\"noopener\">three.js</a> webgl - htc vive - paint");
    }

    private void render() {

        int count = ((BufferGeometry)line.geometry).drawRange.count;
        handleController(controller1);
        handleController(controller2);
        updateGeometry(count, ((BufferGeometry)line.geometry).drawRange.count);
        webGLRenderer.render(scene, camera);
    }
}
