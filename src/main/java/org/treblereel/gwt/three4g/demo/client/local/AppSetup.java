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
import org.treblereel.gwt.three4g.demo.client.local.examples.clipping.WebglClippingIntersection;
import org.treblereel.gwt.three4g.demo.client.local.examples.css2d.Css2dLabel;
import org.treblereel.gwt.three4g.demo.client.local.examples.css3d.Css3dOrthographic;
import org.treblereel.gwt.three4g.demo.client.local.examples.decals.WebglDecals;
import org.treblereel.gwt.three4g.demo.client.local.examples.framebuffer.WebglFramebufferTexture;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometries;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometriesParametric;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryColors;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryColorsJson;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryCube;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryDynamic;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryExtrudeShapes;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryHierarchy;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryHierarchy2;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryNormals;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryShapes;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryTeapot;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryTerrain;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryTerrainFog;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryTerrainRaycast;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryTextShapes;
import org.treblereel.gwt.three4g.demo.client.local.examples.hdr.WebglHdr;
import org.treblereel.gwt.three4g.demo.client.local.examples.interactive.WebglInteractiveBuffergeometry;
import org.treblereel.gwt.three4g.demo.client.local.examples.interactive.WebglInteractiveCubes;
import org.treblereel.gwt.three4g.demo.client.local.examples.interactive.WebglInteractiveCubesGpu;
import org.treblereel.gwt.three4g.demo.client.local.examples.interactive.WebglInteractiveDraggableCubes;
import org.treblereel.gwt.three4g.demo.client.local.examples.interactive.WebglInteractivePoints;
import org.treblereel.gwt.three4g.demo.client.local.examples.lensflares.WebglLensflares;
import org.treblereel.gwt.three4g.demo.client.local.examples.lights.WebglLightsHemisphere;
import org.treblereel.gwt.three4g.demo.client.local.examples.lights.WebglLightsPhysical;
import org.treblereel.gwt.three4g.demo.client.local.examples.lights.WebglLightsPointlights;
import org.treblereel.gwt.three4g.demo.client.local.examples.lights.WebglLightsPointlights2;
import org.treblereel.gwt.three4g.demo.client.local.examples.lights.WebglLightsSpotlight;
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
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsCubemap;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsCubemapBallsReflection;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsCubemapBallsRefraction;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsCubemapDynamic2;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsCubemapRefraction;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsDisplacementmap;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsEnvmaps;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsGrass;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsLightmap;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsTextureAnisotropy;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsTextureCanvas;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsTextureFilters;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsTextureManualmipmap;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsTexturePartialupdate;
import org.treblereel.gwt.three4g.demo.client.local.examples.misc.MiscAnimationGroups;
import org.treblereel.gwt.three4g.demo.client.local.examples.performance.WebglPerformance;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.DayDream;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.Rollercoaster;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.Vive;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.ViveDragging;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.WebVRCubes;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.WebVRPanorama;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.WebVRSandbox;
import org.treblereel.gwt.three4g.demo.client.local.examples.webaudio.WebAudioOrientation;
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
        addListElement(WebglClippingIntersection.name, WebglClippingIntersection.class);
        //decals
        addListElement(WebglDecals.name, WebglDecals.class);
        //effects
        addListElement(WebglFramebufferTexture.name, WebglFramebufferTexture.class);
        //geometry
        addListElement(WebglGeometries.name, WebglGeometries.class);
        addListElement(WebglGeometriesParametric.name, WebglGeometriesParametric.class);
        addListElement(WebglGeometryColors.name, WebglGeometryColors.class);
        addListElement(WebglGeometryColorsJson.name, WebglGeometryColorsJson.class);
        addListElement(WebglGeometryCube.name, WebglGeometryCube.class);
        addListElement(WebglGeometryDynamic.name, WebglGeometryDynamic.class);
        addListElement(WebglGeometryExtrudeShapes.name, WebglGeometryExtrudeShapes.class);
        //addListElement(WebglGeometryExtrudeSplines.name, WebglGeometryExtrudeSplines.class); //TODO
        addListElement(WebglGeometryHierarchy.name, WebglGeometryHierarchy.class);
        addListElement(WebglGeometryHierarchy2.name, WebglGeometryHierarchy2.class);
        addListElement(WebglGeometryNormals.name, WebglGeometryNormals.class);
        addListElement(WebglGeometryShapes.name, WebglGeometryShapes.class);
        addListElement(WebglGeometryTeapot.name, WebglGeometryTeapot.class);
        addListElement(WebglGeometryTerrain.name, WebglGeometryTerrain.class);
        addListElement(WebglGeometryTerrainFog.name, WebglGeometryTerrainFog.class);
        addListElement(WebglGeometryTerrainRaycast.name, WebglGeometryTerrainRaycast.class);
        addListElement(WebglGeometryTextShapes.name, WebglGeometryTextShapes.class);
        //hdr
        addTypeElement("HDR");
        addListElement(WebglHdr.name, WebglHdr.class);
        //loader
        addTypeElement("Loaders");
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
        addListElement(WebglLoaderJsonBlender.name, WebglLoaderJsonBlender.class);
        //addListElement(WebglLoaderColladaKinematics.name, WebglLoaderColladaKinematics.class);


        //lights
        addTypeElement("LIGHTS");
        addListElement(WebglLightsHemisphere.name, WebglLightsHemisphere.class);
        addListElement(WebglLightsPhysical.name, WebglLightsPhysical.class);
        addListElement(WebglLightsPointlights.name, WebglLightsPointlights.class);
        addListElement(WebglLightsPointlights2.name, WebglLightsPointlights2.class);
        addListElement(WebglLightsSpotlight.name, WebglLightsSpotlight.class);
        //addListElement(WebglLightsSpotlights.name, WebglLightsSpotlights.class); //TODO

        //lod
        addTypeElement("LOD");
        addListElement(WebglLod.name, WebglLod.class);
        addTypeElement("MATERIALS");
        addListElement(WebglMaterials.name, WebglMaterials.class);
        addListElement(WebglMaterialsBlending.name, WebglMaterialsBlending.class);
        addListElement(WebglMaterialsBumpmap.name, WebglMaterialsBumpmap.class);
        addListElement(WebglMaterialsBumpmapSkin.name, WebglMaterialsBumpmapSkin.class);
        addListElement(WebglMaterialsChannels.name, WebglMaterialsChannels.class);
        addListElement(WebglMaterialsCubemap.name, WebglMaterialsCubemap.class);
        addListElement(WebglMaterialsCubemapBallsReflection.name, WebglMaterialsCubemapBallsReflection.class);
        addListElement(WebglMaterialsCubemapBallsRefraction.name, WebglMaterialsCubemapBallsRefraction.class);
        addListElement(WebglMaterialsCubemapDynamic2.name, WebglMaterialsCubemapDynamic2.class);
        addListElement(WebglMaterialsCubemapRefraction.name, WebglMaterialsCubemapRefraction.class);
        addListElement(WebglMaterialsDisplacementmap.name, WebglMaterialsDisplacementmap.class);
        addListElement(WebglMaterialsEnvmaps.name, WebglMaterialsEnvmaps.class);
        addListElement(WebglMaterialsGrass.name, WebglMaterialsGrass.class);
        addListElement(WebglMaterialsLightmap.name, WebglMaterialsLightmap.class);
        addListElement(WebglMaterialsTextureAnisotropy.name, WebglMaterialsTextureAnisotropy.class);
        addListElement(WebglMaterialsTextureCanvas.name, WebglMaterialsTextureCanvas.class);
        addListElement(WebglMaterialsTextureFilters.name, WebglMaterialsTextureFilters.class);
        addListElement(WebglMaterialsTextureManualmipmap.name, WebglMaterialsTextureManualmipmap.class);
        addListElement(WebglMaterialsTexturePartialupdate.name, WebglMaterialsTexturePartialupdate.class);

        addTypeElement("PERFORMANCE");
        addListElement(WebglPerformance.name, WebglPerformance.class);
        addTypeElement("INTERACTIVE");
        addListElement(WebglInteractiveBuffergeometry.name, WebglInteractiveBuffergeometry.class);
        addListElement(WebglInteractiveCubes.name, WebglInteractiveCubes.class);
        addListElement(WebglInteractiveCubesGpu.name, WebglInteractiveCubesGpu.class);
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

        //webaudio
        addTypeElement("Web audio");
        addListElement(WebAudioOrientation.name, WebAudioOrientation.class);

        //misc
        addTypeElement("misc");
        addListElement(MiscAnimationGroups.name, MiscAnimationGroups.class);

        //css2d
        addTypeElement("css2d");
        addListElement(Css2dLabel.name, Css2dLabel.class);

        //css3d
        addTypeElement("css3d");
        addListElement(Css3dOrthographic.name, Css3dOrthographic.class);


        appController.dispatch(rootPanel.asWidget());
    }
}
