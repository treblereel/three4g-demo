package org.treblereel.gwt.three4g.demo.client.local.mvc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import elemental2.dom.HTMLDivElement;
import org.treblereel.gwt.three4g.demo.client.local.AppSetup;
import org.treblereel.gwt.three4g.demo.client.local.examples.animation.WebGlAnimationKeyframesJson;
import org.treblereel.gwt.three4g.demo.client.local.examples.animation.WebglAnimationScene;
import org.treblereel.gwt.three4g.demo.client.local.examples.camera.CanvasCameraOrthographic;
import org.treblereel.gwt.three4g.demo.client.local.examples.camera.WebGlCamera;
import org.treblereel.gwt.three4g.demo.client.local.examples.camera.WebglCameraArray;
import org.treblereel.gwt.three4g.demo.client.local.examples.clipping.WebglClipping;
import org.treblereel.gwt.three4g.demo.client.local.examples.decals.WebglDecals;
import org.treblereel.gwt.three4g.demo.client.local.examples.framebuffer.WebglFramebufferTexture;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometriesParametric;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryCube;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryDynamic;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryTerrain;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryTerrainFog;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryTerrainRaycast;
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
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.VivePaint;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.ViveSculpt;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.WebVRCubes;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.WebVRPanorama;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.WebVRSandbox;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.CanvasCameraOrthographicPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.DayDreamPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.MainPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.Presenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.RollercoasterPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.ViveDraggingPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.VivePaintPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.VivePresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.ViveSculptPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebGlAnimationKeyframesJsonPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebGlCameraPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebVRCubesPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebVRPanoramaPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebVRSandboxPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglAnimationScenePresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglCameraArrayPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglClippingPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglDecalsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglFramebufferTexturePresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometriesParametricPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryCubePresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryDynamicPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryTerrainFogPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryTerrainPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryTerrainRaycastPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglInteractiveBuffergeometryPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglInteractiveDraggableCubesPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglInteractivePointsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLensflaresPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoader3dsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderBabylonPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderColladaPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderColladaSkinningPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderDracoPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderFbxPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderGltfPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderJsonBlenderPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderObj2Presenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderObjPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderPcdPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderPdbPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderPrwmPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderTextureTgaPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLodPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsBlendingPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsBumpmapPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsBumpmapSkinPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsChannelsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.view.MainView;


public class AppController implements Presenter, ValueChangeHandler<String> {

    private HandlerManager eventBus = new HandlerManager(null);


    private MainPresenter mainPresenter = GWT.create(MainPresenter.class);
    private WebGlAnimationKeyframesJsonPresenter webGlAnimationKeyframesJsonPresenter = GWT.create(WebGlAnimationKeyframesJsonPresenter.class);
    private WebglAnimationScenePresenter webglAnimationScenePresenter = GWT.create(WebglAnimationScenePresenter.class);
    private WebGlCameraPresenter webGlCameraPresenter = GWT.create(WebGlCameraPresenter.class);
    private WebglCameraArrayPresenter webglCameraArrayPresenter = GWT.create(WebglCameraArrayPresenter.class);
    private WebglGeometriesParametricPresenter webglGeometriesParametricPresenter = GWT.create(WebglGeometriesParametricPresenter.class);
    private WebglGeometryCubePresenter webglGeometryCubePresenter = GWT.create(WebglGeometryCubePresenter.class);
    private WebglGeometryDynamicPresenter webglGeometryDynamicPresenter = GWT.create(WebglGeometryDynamicPresenter.class);
    private DayDreamPresenter dayDreamPresenter = GWT.create(DayDreamPresenter.class);
    private RollercoasterPresenter rollercoasterPresenter = GWT.create(RollercoasterPresenter.class);
    private VivePresenter vivePresenter = GWT.create(VivePresenter.class);
    private ViveDraggingPresenter viveDraggingPresenter = GWT.create(ViveDraggingPresenter.class);
    private VivePaintPresenter vivePaintPresenter = GWT.create(VivePaintPresenter.class);
    private ViveSculptPresenter viveSculptPresenter = GWT.create(ViveSculptPresenter.class);
    private WebglInteractivePointsPresenter webglInteractivePointsPresenter = GWT.create(WebglInteractivePointsPresenter.class);
    private WebglInteractiveBuffergeometryPresenter webglInteractiveBuffergeometryPresenter = GWT.create(WebglInteractiveBuffergeometryPresenter.class);
    private WebglLoaderJsonBlenderPresenter webglLoaderJsonBlenderPresenter = GWT.create(WebglLoaderJsonBlenderPresenter.class);
    private WebglLoader3dsPresenter webglLoader3dsPresenter = GWT.create(WebglLoader3dsPresenter.class);
    private WebglLoaderColladaPresenter webglLoaderColladaPresenter = GWT.create(WebglLoaderColladaPresenter.class);
    private WebglLoaderColladaSkinningPresenter webglLoaderColladaSkinningPresenter = GWT.create(WebglLoaderColladaSkinningPresenter.class);
    private WebglLoaderDracoPresenter webglLoaderDracoPresenter = GWT.create(WebglLoaderDracoPresenter.class);
    private WebglLoaderFbxPresenter webglLoaderFbxPresenter = GWT.create(WebglLoaderFbxPresenter.class);
    private WebglLoaderGltfPresenter webglLoaderGltfPresenter = GWT.create(WebglLoaderGltfPresenter.class);
    private WebglLoaderBabylonPresenter webglLoaderBabylonPresenter = GWT.create(WebglLoaderBabylonPresenter.class);
    private WebglLoaderObjPresenter webglLoaderObjPresenter = GWT.create(WebglLoaderObjPresenter.class);
    private WebglLoaderObj2Presenter webglLoaderObj2Presenter = GWT.create(WebglLoaderObj2Presenter.class);
    private WebglLoaderPcdPresenter webglLoaderPcdPresenter = GWT.create(WebglLoaderPcdPresenter.class);
    private WebglLoaderPdbPresenter webglLoaderPdbPresenter = GWT.create(WebglLoaderPdbPresenter.class);
    private WebglLoaderPrwmPresenter webglLoaderPrwmPresenter = GWT.create(WebglLoaderPrwmPresenter.class);
    private WebglLoaderTextureTgaPresenter webglLoaderTextureTgaPresenter = GWT.create(WebglLoaderTextureTgaPresenter.class);
    private WebVRCubesPresenter webVRCubesPresenter = GWT.create(WebVRCubesPresenter.class);
    private WebVRPanoramaPresenter webVRPanoramaPresenter = GWT.create(WebVRPanoramaPresenter.class);
    private WebVRSandboxPresenter webVRSandboxPresenter = GWT.create(WebVRSandboxPresenter.class);
    private WebglLensflaresPresenter webglLensflaresPresenter = GWT.create(WebglLensflaresPresenter.class);
    private WebglInteractiveDraggableCubesPresenter webglInteractiveDraggableCubesPresenter = GWT.create(WebglInteractiveDraggableCubesPresenter.class);
    private WebglClippingPresenter webglClippingPresenter = GWT.create(WebglClippingPresenter.class);
    private WebglDecalsPresenter webglDecalsPresenter = GWT.create(WebglDecalsPresenter.class);
    private WebglLodPresenter webglLodPresenter = GWT.create(WebglLodPresenter.class);
    private WebglMaterialsPresenter webglMaterialsPresenter = GWT.create(WebglMaterialsPresenter.class);
    private WebglMaterialsBlendingPresenter webglMaterialsBlendingPresenter = GWT.create(WebglMaterialsBlendingPresenter.class);
    private WebglMaterialsBumpmapPresenter webglMaterialsBumpmapPresenter = GWT.create(WebglMaterialsBumpmapPresenter.class);
    private WebglMaterialsBumpmapSkinPresenter webglMaterialsBumpmapSkinPresenter = GWT.create(WebglMaterialsBumpmapSkinPresenter.class);
    private WebglMaterialsChannelsPresenter webglMaterialsChannelsPresenter = GWT.create(WebglMaterialsChannelsPresenter.class);
    private CanvasCameraOrthographicPresenter canvasCameraOrthographicPresenter = GWT.create(CanvasCameraOrthographicPresenter.class);
    private WebglFramebufferTexturePresenter webglFramebufferTexturePresenter = GWT.create(WebglFramebufferTexturePresenter.class);
    private WebglGeometryTerrainPresenter webglGeometryTerrainPresenter = GWT.create(WebglGeometryTerrainPresenter.class);
    private WebglGeometryTerrainFogPresenter webglGeometryTerrainFogPresenter = GWT.create(WebglGeometryTerrainFogPresenter.class);
    private WebglGeometryTerrainRaycastPresenter webglGeometryTerrainRaycastPresenter = GWT.create(WebglGeometryTerrainRaycastPresenter.class);


    private HTMLDivElement container;


    public AppController() {
        bind();
    }

    public void bind() {
        History.addValueChangeHandler(this);
        eventBus.addHandler(MainView.TYPE, event -> MainView.class.getSimpleName());
        eventBus.addHandler(WebGlAnimationKeyframesJson.TYPE, event -> History.newItem(WebGlAnimationKeyframesJson.class.getSimpleName()));
        eventBus.addHandler(WebglAnimationScene.TYPE, event -> History.newItem(WebglAnimationScene.class.getSimpleName()));
        eventBus.addHandler(WebGlCamera.TYPE, event -> History.newItem(WebGlCamera.class.getSimpleName()));
        eventBus.addHandler(WebglCameraArray.TYPE, event -> History.newItem(WebglCameraArray.class.getSimpleName()));
        eventBus.addHandler(WebglGeometriesParametric.TYPE, event -> History.newItem(WebglGeometriesParametric.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryCube.TYPE, event -> History.newItem(WebglGeometryCube.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryDynamic.TYPE, event -> History.newItem(WebglGeometryDynamic.class.getSimpleName()));
        eventBus.addHandler(DayDream.TYPE, event -> History.newItem(DayDream.class.getSimpleName()));
        eventBus.addHandler(Rollercoaster.TYPE, event -> History.newItem(Rollercoaster.class.getSimpleName()));
        eventBus.addHandler(Vive.TYPE, event -> History.newItem(Vive.class.getSimpleName()));
        eventBus.addHandler(ViveDragging.TYPE, event -> History.newItem(ViveDragging.class.getSimpleName()));
        eventBus.addHandler(VivePaint.TYPE, event -> History.newItem(VivePaint.class.getSimpleName()));
        eventBus.addHandler(ViveSculpt.TYPE, event -> History.newItem(ViveSculpt.class.getSimpleName()));
        eventBus.addHandler(WebglInteractivePoints.TYPE, event -> History.newItem(WebglInteractivePoints.class.getSimpleName()));
        eventBus.addHandler(WebglInteractiveBuffergeometry.TYPE, event -> History.newItem(WebglInteractiveBuffergeometry.class.getSimpleName()));
        eventBus.addHandler(WebglLoaderJsonBlender.TYPE, event -> History.newItem(WebglLoaderJsonBlender.class.getSimpleName()));
        eventBus.addHandler(WebglLoader3ds.TYPE, event -> History.newItem(WebglLoader3ds.class.getSimpleName()));
        eventBus.addHandler(WebglLoaderCollada.TYPE, event -> History.newItem(WebglLoaderCollada.class.getSimpleName()));
        eventBus.addHandler(WebglLoaderColladaSkinning.TYPE, event -> History.newItem(WebglLoaderColladaSkinning.class.getSimpleName()));
        eventBus.addHandler(WebglLoaderDraco.TYPE, event -> History.newItem(WebglLoaderDraco.class.getSimpleName()));
        eventBus.addHandler(WebglLoaderFbx.TYPE, event -> History.newItem(WebglLoaderFbx.class.getSimpleName()));
        eventBus.addHandler(WebglLoaderGltf.TYPE, event -> History.newItem(WebglLoaderGltf.class.getSimpleName()));
        eventBus.addHandler(WebglLoaderBabylon.TYPE, event -> History.newItem(WebglLoaderBabylon.class.getSimpleName()));
        eventBus.addHandler(WebglLoaderObj.TYPE, event -> History.newItem(WebglLoaderObj.class.getSimpleName()));
        eventBus.addHandler(WebglLoaderObj2.TYPE, event -> History.newItem(WebglLoaderObj2.class.getSimpleName()));
        eventBus.addHandler(WebglLoaderPcd.TYPE, event -> History.newItem(WebglLoaderPcd.class.getSimpleName()));
        eventBus.addHandler(WebglLoaderPdb.TYPE, event -> History.newItem(WebglLoaderPdb.class.getSimpleName()));
        eventBus.addHandler(WebglLoaderPrwm.TYPE, event -> History.newItem(WebglLoaderPrwm.class.getSimpleName()));
        eventBus.addHandler(WebglLoaderTextureTga.TYPE, event -> History.newItem(WebglLoaderTextureTga.class.getSimpleName()));
        eventBus.addHandler(WebVRCubes.TYPE, event -> History.newItem(WebVRCubes.class.getSimpleName()));
        eventBus.addHandler(WebVRPanorama.TYPE, event -> History.newItem(WebVRPanorama.class.getSimpleName()));
        eventBus.addHandler(WebVRSandbox.TYPE, event -> History.newItem(WebVRSandbox.class.getSimpleName()));
        eventBus.addHandler(WebglLensflares.TYPE, event -> History.newItem(WebglLensflares.class.getSimpleName()));
        eventBus.addHandler(WebglInteractiveDraggableCubes.TYPE, event -> History.newItem(WebglInteractiveDraggableCubes.class.getSimpleName()));
        eventBus.addHandler(WebglClipping.TYPE, event -> History.newItem(WebglClipping.class.getSimpleName()));
        eventBus.addHandler(WebglDecals.TYPE, event -> History.newItem(WebglDecals.class.getSimpleName()));
        eventBus.addHandler(WebglLod.TYPE, event -> History.newItem(WebglLod.class.getSimpleName()));
        eventBus.addHandler(WebglMaterials.TYPE, event -> History.newItem(WebglMaterials.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsBlending.TYPE, event -> History.newItem(WebglMaterialsBlending.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsBumpmap.TYPE, event -> History.newItem(WebglMaterialsBumpmap.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsBumpmapSkin.TYPE, event -> History.newItem(WebglMaterialsBumpmapSkin.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsChannels.TYPE, event -> History.newItem(WebglMaterialsChannels.class.getSimpleName()));
        eventBus.addHandler(CanvasCameraOrthographic.TYPE, event -> History.newItem(CanvasCameraOrthographic.class.getSimpleName()));
        eventBus.addHandler(WebglFramebufferTexture.TYPE, event -> History.newItem(WebglFramebufferTexture.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryTerrain.TYPE, event -> History.newItem(WebglGeometryTerrain.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryTerrainFog.TYPE, event -> History.newItem(WebglGeometryTerrainFog.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryTerrainRaycast.TYPE, event -> History.newItem(WebglGeometryTerrainRaycast.class.getSimpleName()));


    }

    public void dispatch(final HTMLDivElement container) {
        this.container = container;
        if ("".equals(History.getToken())) {
            History.newItem(WebGlAnimationKeyframesJson.class.getSimpleName());
        } else {
            History.fireCurrentHistoryState();
        }
    }

    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();

        for (int i = 0; i < container.childElementCount; i++) {
            container.removeChild(container.childNodes.item(i));
        }
        if (AppSetup.currentPageHolder.getCurrentPage() != null)
            AppSetup.currentPageHolder.getCurrentPage().detach();

        if (token != null) {
            if (token.equals(MainView.class.getSimpleName())) {
                mainPresenter.dispatch(container);
            } else if (token.equals(WebGlAnimationKeyframesJson.class.getSimpleName())) {
                webGlAnimationKeyframesJsonPresenter.dispatch(container);
            } else if (token.equals(WebglAnimationScene.class.getSimpleName())) {
                webglAnimationScenePresenter.dispatch(container);
            } else if (token.equals(WebGlCamera.class.getSimpleName())) {
                webGlCameraPresenter.dispatch(container);
            } else if (token.equals(WebglCameraArray.class.getSimpleName())) {
                webglCameraArrayPresenter.dispatch(container);
            } else if (token.equals(WebglGeometriesParametric.class.getSimpleName())) {
                webglGeometriesParametricPresenter.dispatch(container);
            } else if (token.equals(WebglGeometryCube.class.getSimpleName())) {
                webglGeometryCubePresenter.dispatch(container);
            } else if (token.equals(WebglGeometryDynamic.class.getSimpleName())) {
                webglGeometryDynamicPresenter.dispatch(container);
            } else if (token.equals(DayDream.class.getSimpleName())) {
                dayDreamPresenter.dispatch(container);
            } else if (token.equals(Rollercoaster.class.getSimpleName())) {
                rollercoasterPresenter.dispatch(container);
            } else if (token.equals(Vive.class.getSimpleName())) {
                vivePresenter.dispatch(container);
            } else if (token.equals(ViveDragging.class.getSimpleName())) {
                viveDraggingPresenter.dispatch(container);
            } else if (token.equals(VivePaint.class.getSimpleName())) {
                vivePaintPresenter.dispatch(container);
            } else if (token.equals(ViveSculpt.class.getSimpleName())) {
                viveSculptPresenter.dispatch(container);
            } else if (token.equals(WebglInteractivePoints.class.getSimpleName())) {
                webglInteractivePointsPresenter.dispatch(container);
            } else if (token.equals(WebglInteractiveBuffergeometry.class.getSimpleName())) {
                webglInteractiveBuffergeometryPresenter.dispatch(container);
            } else if (token.equals(WebglLoaderJsonBlender.class.getSimpleName())) {
                webglLoaderJsonBlenderPresenter.dispatch(container);
            } else if (token.equals(WebglLoader3ds.class.getSimpleName())) {
                webglLoader3dsPresenter.dispatch(container);
            } else if (token.equals(WebglLoaderCollada.class.getSimpleName())) {
                webglLoaderColladaPresenter.dispatch(container);
            } else if (token.equals(WebglLoaderColladaSkinning.class.getSimpleName())) {
                webglLoaderColladaSkinningPresenter.dispatch(container);
            } else if (token.equals(WebglLoaderDraco.class.getSimpleName())) {
                webglLoaderDracoPresenter.dispatch(container);
            } else if (token.equals(WebglLoaderFbx.class.getSimpleName())) {
                webglLoaderFbxPresenter.dispatch(container);
            } else if (token.equals(WebglLoaderGltf.class.getSimpleName())) {
                webglLoaderGltfPresenter.dispatch(container);
            } else if (token.equals(WebglLoaderBabylon.class.getSimpleName())) {
                webglLoaderBabylonPresenter.dispatch(container);
            } else if (token.equals(WebglLoaderObj.class.getSimpleName())) {
                webglLoaderObjPresenter.dispatch(container);
            } else if (token.equals(WebglLoaderObj2.class.getSimpleName())) {
                webglLoaderObj2Presenter.dispatch(container);
            } else if (token.equals(WebglLoaderPcd.class.getSimpleName())) {
                webglLoaderPcdPresenter.dispatch(container);
            } else if (token.equals(WebglLoaderPdb.class.getSimpleName())) {
                webglLoaderPdbPresenter.dispatch(container);
            } else if (token.equals(WebglLoaderPrwm.class.getSimpleName())) {
                webglLoaderPrwmPresenter.dispatch(container);
            } else if (token.equals(WebglLoaderTextureTga.class.getSimpleName())) {
                webglLoaderTextureTgaPresenter.dispatch(container);
            } else if (token.equals(WebVRCubes.class.getSimpleName())) {
                webVRCubesPresenter.dispatch(container);
            } else if (token.equals(WebVRPanorama.class.getSimpleName())) {
                webVRPanoramaPresenter.dispatch(container);
            } else if (token.equals(WebVRSandbox.class.getSimpleName())) {
                webVRSandboxPresenter.dispatch(container);
            } else if (token.equals(WebglLensflares.class.getSimpleName())) {
                webglLensflaresPresenter.dispatch(container);
            } else if (token.equals(WebglInteractiveDraggableCubes.class.getSimpleName())) {
                webglInteractiveDraggableCubesPresenter.dispatch(container);
            } else if (token.equals(WebglClipping.class.getSimpleName())) {
                webglClippingPresenter.dispatch(container);
            } else if (token.equals(WebglDecals.class.getSimpleName())) {
                webglDecalsPresenter.dispatch(container);
            } else if (token.equals(WebglLod.class.getSimpleName())) {
                webglLodPresenter.dispatch(container);
            } else if (token.equals(WebglMaterials.class.getSimpleName())) {
                webglMaterialsPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsBlending.class.getSimpleName())) {
                webglMaterialsBlendingPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsBumpmap.class.getSimpleName())) {
                webglMaterialsBumpmapPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsBumpmapSkin.class.getSimpleName())) {
                webglMaterialsBumpmapSkinPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsChannels.class.getSimpleName())) {
                webglMaterialsChannelsPresenter.dispatch(container);
            } else if (token.equals(CanvasCameraOrthographic.class.getSimpleName())) {
                canvasCameraOrthographicPresenter.dispatch(container);
            } else if (token.equals(WebglFramebufferTexture.class.getSimpleName())) {
                webglFramebufferTexturePresenter.dispatch(container);
            } else if (token.equals(WebglGeometryTerrain.class.getSimpleName())) {
                webglGeometryTerrainPresenter.dispatch(container);
            } else if (token.equals(WebglGeometryTerrainFog.class.getSimpleName())) {
                webglGeometryTerrainFogPresenter.dispatch(container);
            } else if (token.equals(WebglGeometryTerrainRaycast.class.getSimpleName())) {
                webglGeometryTerrainRaycastPresenter.dispatch(container);
            }
        }
    }
}