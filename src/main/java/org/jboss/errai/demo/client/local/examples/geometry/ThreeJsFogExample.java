package org.jboss.errai.demo.client.local.examples.geometry;

import static elemental2.dom.DomGlobal.document;

import com.google.gwt.animation.client.AnimationScheduler;
import elemental2.core.Float32Array;
import elemental2.core.JsObject;
import elemental2.core.ObjectPropertyDescriptor;
import elemental2.core.Uint8Array;
import elemental2.core.Uint8ClampedArray;
import elemental2.dom.CanvasRenderingContext2D;
import elemental2.dom.HTMLCanvasElement;
import elemental2.dom.ImageData;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import jsinterop.base.Js;
import org.jboss.errai.demo.client.api.FirstPersonControls;
import org.jboss.errai.demo.client.api.ImprovedNoise;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.examples.geometry.css.GeometryCssClientBundle;
import org.jboss.errai.demo.client.local.resources.JavascriptTextResource;
import org.jboss.errai.ioc.client.api.LoadAsync;
import org.treblereel.gwt.three4g.Constants;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.Clock;
import org.treblereel.gwt.three4g.core.Color;
import org.treblereel.gwt.three4g.geometries.PlaneBufferGeometry;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterial;
import org.treblereel.gwt.three4g.materials.MeshBasicMaterialParameters;
import org.treblereel.gwt.three4g.math.Vector3;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.scenes.FogExp2;
import org.treblereel.gwt.three4g.scenes.Scene;
import org.treblereel.gwt.three4g.textures.CanvasTexture;

/**
 * @author Dmitrii Tikhomirov <chani@me.com> Created by treblereel on 4/16/18.
 */
@LoadAsync
@ApplicationScoped
public class ThreeJsFogExample extends Attachable {

  int worldWidth = 256, worldDepth = 256;
  int worldHalfWidth = worldWidth / 2, worldHalfDepth = worldDepth / 2;

  private Scene scene;
  private PerspectiveCamera camera;
  private FirstPersonControls controls;
  private Clock clock = new Clock();


  @PostConstruct
  public void init() {

    loadJavaScript(JavascriptTextResource.IMPL.getImprovedNoise().getText());
    loadJavaScript(JavascriptTextResource.IMPL.getFirstPersonControls().getText());

    GeometryCssClientBundle.IMPL.fogAnimationScene().ensureInjected();

    this.camera = new PerspectiveCamera(60, window.innerWidth / window.innerHeight, 1, 10000);
    this.controls = new FirstPersonControls(camera);
    controls.movementSpeed = 150;
    controls.lookSpeed = 0.1;

    this.scene = new Scene();
    scene.background = new Color(0xefd1b5);
    scene.fog = new FogExp2(new Color(0xefd1b5), (float) 0.0025);

    Uint8Array data = generateHeight(worldWidth, worldDepth);
    camera.position.y = (float) (data.getAt(worldHalfWidth + worldHalfDepth * worldWidth) * 10
        + 500);

    PlaneBufferGeometry geometry = new PlaneBufferGeometry(7500, 7500, worldWidth - 1,
        worldDepth - 1);
    geometry.rotateX((float) -Math.PI / 2);

    Float32Array vertices = Js.uncheckedCast(
        JsObject.getOwnPropertyDescriptor(geometry.getAttribute("position"), "array").getValue());
    for (int i = 0, j = 0, l = vertices.length; i < l && j < l - 1; i++, j += 3) {
      vertices.setAt(j + 1, data.getAt(i) * 10);
    }

    CanvasTexture texture = new CanvasTexture(generateTexture(data, worldWidth, worldDepth));
    texture.wrapS = Constants.ClampToEdgeWrapping;
    texture.wrapT = Constants.ClampToEdgeWrapping;

    MeshBasicMaterialParameters params = new MeshBasicMaterialParameters();
    params.map = texture;
    Mesh mesh = new Mesh(geometry, new MeshBasicMaterial(params));
    scene.add(mesh);

    webGLRenderer = new WebGLRenderer();
    setupWebGLRenderer(webGLRenderer);

    window.addEventListener("resize", evt -> onWindowResize(), false);
  }

  private Uint8Array generateHeight(int width, int height) {
    int size = width * height;
    Uint8Array data = new Uint8Array(size);

    ImprovedNoise perlin = new ImprovedNoise();
    int quality = 1;
    double z = Math.random() * 100;
    for (int j = 0; j < 4; j++) {
      for (int i = 0; i < size; i++) {
        int x = i % width, y = ~~(i / width);
        data.setAt(i, data.getAt(i) + Math
            .abs(perlin.noise(x / quality, y / quality, (float) z) * quality * 1.75));
      }
      quality *= 5;
    }
    return data;
  }

  private HTMLCanvasElement generateTexture(Uint8Array data, int width, int height) {
    Vector3 vector3 = new Vector3(0, 0, 0);
    Vector3 sun = new Vector3(1, 1, 1);
    sun.normalize();
    HTMLCanvasElement canvas = Js.uncheckedCast(document.createElement("canvas"));
    canvas.width = width;
    canvas.height = height;
    CanvasRenderingContext2D context = Js.uncheckedCast(canvas.getContext("2d"));
    ObjectPropertyDescriptor fillStyle = ObjectPropertyDescriptor.create();
    fillStyle.setValue("#000");
    JsObject.defineProperty(context, "fillStyle", fillStyle);
    context.fillRect(0, 0, width, height);
    ImageData image = context.getImageData(0, 0, canvas.width, canvas.height);
    Uint8ClampedArray imageData = image.data;
    for (int i = 0, j = 0, l = imageData.length; i < l; i += 4, j++) {
      if (j - 2 < 0 || j + 2 > data.length - 1) {
        vector3.x = Float.NaN;
      } else {
        vector3.x = (float) (data.getAt(j - 2) - data.getAt(j + 2));
      }
      vector3.y = 2;
      if (j - width * 2 < 0 || j + width * 2 > data.length - 1) {
        vector3.z = Float.NaN;
      } else {
        vector3.z = (float) (data.getAt(j - width * 2) - data.getAt(j + width * 2));
      }
      vector3.normalize();
      float shade = vector3.dot(sun);

      imageData.setAt(i, (96 + shade * 128) * (0.5 + data.getAt(j) * 0.007));
      imageData.setAt(i + 1, (32 + shade * 96) * (0.5 + data.getAt(j) * 0.007));
      imageData.setAt(i + 2, (shade * 96) * (0.5 + data.getAt(j) * 0.007));
    }
    context.putImageData(image, 0, 0);

    // Scaled 4x
    HTMLCanvasElement canvasScaled = Js.uncheckedCast(document.createElement("canvas"));
    canvasScaled.width = width * 4;
    canvasScaled.height = height * 4;

    context = Js.uncheckedCast(canvasScaled.getContext("2d"));
    context.scale(4, 4);
    context.drawImage(canvas, 0, 0);

    image = context.getImageData(0, 0, canvasScaled.width, canvasScaled.height);
    imageData = image.data;
    for (int i = 0, l = imageData.length; i < l; i += 4) {
      double v = ~~((int) Math.random() * 5);
      imageData.setAt(i, v);
      imageData.setAt(i + 1, v);
      imageData.setAt(i + 2, v);
    }
    context.putImageData(image, 0, 0);
    return canvasScaled;

  }

  @Override
  protected void doAttachScene() {
    document.body.appendChild(webGLRenderer.domElement);
    animate();
  }

  @Override
  protected void doAttachInfo() {
    info.show().setHrefToInfo("http://threejs.org").setTextContentToInfo("three.js").setInnetHtml("webgl - geometry - terrain + fog");
  }

  private void animate() {
    AnimationScheduler.get().requestAnimationFrame(timestamp -> {
      if (webGLRenderer.domElement != null) {
        animate();
      }
    });
    render();
  }

  private void render() {
    controls.update(clock.getDelta());
    webGLRenderer.render(scene, camera);
  }

}
