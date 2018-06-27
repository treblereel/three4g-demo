package org.treblereel.gwt.three4g.demo.client.local;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLUListElement;
import elemental2.dom.Node;
import org.treblereel.gwt.three4g.demo.client.local.examples.animation.WebGlAnimationKeyframesJson;
import org.treblereel.gwt.three4g.demo.client.local.examples.animation.WebglAnimationScene;
import org.treblereel.gwt.three4g.demo.client.local.examples.camera.CanvasCameraOrthographic;
import org.treblereel.gwt.three4g.demo.client.local.examples.camera.WebGlCamera;
import org.treblereel.gwt.three4g.demo.client.local.examples.camera.WebglCameraArray;
import org.treblereel.gwt.three4g.demo.client.local.examples.clipping.WebglClipping;
import org.treblereel.gwt.three4g.demo.client.local.examples.decals.WebglDecals;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometriesParametric;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryCube;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryDynamic;
import org.treblereel.gwt.three4g.demo.client.local.examples.interactive.WebglInteractiveBuffergeometry;
import org.treblereel.gwt.three4g.demo.client.local.examples.interactive.WebglInteractiveDraggableCubes;
import org.treblereel.gwt.three4g.demo.client.local.examples.interactive.WebglInteractivePoints;
import org.treblereel.gwt.three4g.demo.client.local.examples.lensflares.WebglLensflares;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoader3ds;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderBabylon;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderCollada;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderColladaSkinning;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderDraco;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderFbx;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderGltf;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderJsonBlender;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderObj;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderObj2;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderPcd;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderPdb;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderPrwm;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderTextureTga;
import org.treblereel.gwt.three4g.demo.client.local.examples.lod.WebglLod;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterials;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsBlending;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsBumpmap;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsBumpmapSkin;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsChannels;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.DayDream;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.Rollercoaster;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.Vive;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.ViveDragging;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.WebVRCubes;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.WebVRPanorama;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.WebVRSandbox;
import org.treblereel.gwt.three4g.demo.client.local.mvc.AppController;
import org.treblereel.gwt.three4g.demo.client.local.mvc.view.RootPanel;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 5/31/18.
 */
public class AppSetup implements EntryPoint {

    public static ViewSrcButton viewSrcButton = new ViewSrcButton();
    public static InfoDiv infoDiv = new InfoDiv();
    public static GuiDiv guiDiv = new GuiDiv();
    public static MenuDiv menuDiv = new MenuDiv();
    public static CurrentPageHolder currentPageHolder = new CurrentPageHolder();

    private HTMLUListElement list = (HTMLUListElement) DomGlobal.document.createElement("ul");
    private HTMLDivElement menu = (HTMLDivElement) DomGlobal.document.createElement("div");
    private RootPanel rootPanel = new RootPanel();
    private AppController appController = new AppController();


    public AppSetup() {

    }

    private void addListElement(String name, Class clazz) {
        HTMLAnchorElement elm = (HTMLAnchorElement) DomGlobal.document.createElement("a");
        elm.textContent = name;
        elm.classList.add("link");
        elm.addEventListener("click", evt -> {
            clearAndSetSelected(elm);
            Window.Location.assign("#" + clazz.getSimpleName());
        });
        list.appendChild(elm);
    }

    private void addTypeElement(String name) {
        HTMLAnchorElement elm = (HTMLAnchorElement) DomGlobal.document.createElement("a");
        elm.textContent = name.toUpperCase();
        elm.classList.add("type");
        list.appendChild(elm);
    }

    private void clearAndSetSelected(HTMLAnchorElement elm) {
        for (int i = 0; i < list.childNodes.length; i++) {
            Node node = list.childNodes.item(i);
            if (node.nodeType == Node.ELEMENT_NODE) {
                HTMLElement element = ((HTMLElement) node);
                if (element.classList.contains("selected")) {
                    element.classList.remove("selected");
                }
            }
        }
        elm.classList.add("selected");
        for (int i = 0; i < document.body.childNodes.length; i++) {
            if (document.body.childNodes.item(i).nodeType == Node.ELEMENT_NODE) {
                HTMLElement element = ((HTMLElement) document.body.childNodes.item(i));
                if (element.tagName.toLowerCase().equals("canvas")) {
                    element.remove();
                }
            }
        }
    }

    @Override
    public void onModuleLoad() {


        menu.id = "panel";
        menu.appendChild(list);

        DomGlobal.document.body.appendChild(menu);
        DomGlobal.document.body.appendChild(rootPanel.asWidget());


        //addListElement("Intro", MainView.class);
        addTypeElement("WebGL");
        //animation
        addListElement(WebGlAnimationKeyframesJson.name, WebGlAnimationKeyframesJson.class);
        addListElement(WebglAnimationScene.name, WebglAnimationScene.class);
        //camera
        addListElement(WebGlCamera.name, WebGlCamera.class);
        addListElement(WebglCameraArray.name, WebglCameraArray.class);
        addListElement(CanvasCameraOrthographic.name, CanvasCameraOrthographic.class);
        //clipping
        addListElement(WebglClipping.name, WebglClipping.class);
        //decals
        addListElement(WebglDecals.name, WebglDecals.class);
        //geometry
        addListElement(WebglGeometriesParametric.name, WebglGeometriesParametric.class);
        addListElement(WebglGeometryCube.name, WebglGeometryCube.class);
        addListElement(WebglGeometryDynamic.name, WebglGeometryDynamic.class);
        //loader
        addListElement(WebglLoaderBabylon.name, WebglLoaderBabylon.class);
        addListElement(WebglLoader3ds.name, WebglLoader3ds.class);
        addListElement(WebglLoaderCollada.name, WebglLoaderCollada.class);
        addListElement(WebglLoaderColladaSkinning.name, WebglLoaderColladaSkinning.class);
        addListElement(WebglLoaderDraco.name, WebglLoaderDraco.class);
        addListElement(WebglLoaderFbx.name, WebglLoaderFbx.class);
        addListElement(WebglLoaderGltf.name, WebglLoaderGltf.class);
        addListElement(WebglLoaderObj.name, WebglLoaderObj.class);
        addListElement(WebglLoaderObj2.name, WebglLoaderObj2.class);
        addListElement(WebglLoaderPcd.name, WebglLoaderPcd.class);
        addListElement(WebglLoaderPdb.name, WebglLoaderPdb.class);
        addListElement(WebglLoaderPrwm.name, WebglLoaderPrwm.class);

        addListElement(WebglLoaderTextureTga.name, WebglLoaderTextureTga.class);
        //lod
        addListElement(WebglLod.name, WebglLod.class);

        //addListElement(WebglLoaderColladaKinematics.name, WebglLoaderColladaKinematics.class);
        addListElement(WebglLoaderJsonBlender.name, WebglLoaderJsonBlender.class);
        //material
        addListElement(WebglMaterials.name, WebglMaterials.class);
        addListElement(WebglMaterialsBlending.name, WebglMaterialsBlending.class);
        addListElement(WebglMaterialsBumpmap.name, WebglMaterialsBumpmap.class);
        addListElement(WebglMaterialsBumpmapSkin.name, WebglMaterialsBumpmapSkin.class);
        addListElement(WebglMaterialsChannels.name, WebglMaterialsChannels.class);

        //interactive
        addListElement(WebglInteractiveBuffergeometry.name, WebglInteractiveBuffergeometry.class);
        addListElement(WebglInteractiveDraggableCubes.name, WebglInteractiveDraggableCubes.class);
        addListElement(WebglInteractivePoints.name, WebglInteractivePoints.class);
        //Lensflares
        addListElement(WebglLensflares.name, WebglLensflares.class);
        //vr
        addTypeElement("VR");
        addListElement(DayDream.name, DayDream.class);
        addListElement(WebVRCubes.name, WebVRCubes.class);
        addListElement(WebVRPanorama.name, WebVRPanorama.class);
        addListElement(Rollercoaster.name, Rollercoaster.class);
        addListElement(WebVRSandbox.name, WebVRSandbox.class);
        addListElement(Vive.name, Vive.class);
        addListElement(ViveDragging.name, ViveDragging.class);
        //addListElement(VivePaint.name, VivePaint.class);
        //addListElement(ViveSculpt.name, ViveSculpt.class);


        appController.dispatch(rootPanel.asWidget());
    }
}
