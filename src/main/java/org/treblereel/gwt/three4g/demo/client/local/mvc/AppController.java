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
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryExtrudeSplines;
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
import org.treblereel.gwt.three4g.demo.client.local.examples.lights.WebglLightsSpotlights;
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
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsTextureRotation;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsTransparency;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsVariationsBasic;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsVariationsLambert;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsVariationsPhong;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsVariationsPhysical;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsVariationsStandard;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsVariationsToon;
import org.treblereel.gwt.three4g.demo.client.local.examples.material.WebglMaterialsWireframe;
import org.treblereel.gwt.three4g.demo.client.local.examples.misc.MiscAnimationGroups;
import org.treblereel.gwt.three4g.demo.client.local.examples.modifiers.WebglModifierSimplifier;
import org.treblereel.gwt.three4g.demo.client.local.examples.modifiers.WebglModifierTessellation;
import org.treblereel.gwt.three4g.demo.client.local.examples.performance.WebglPerformance;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.DayDream;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.Rollercoaster;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.Vive;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.ViveDragging;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.VivePaint;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.ViveSculpt;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.WebVRCubes;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.WebVRPanorama;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.WebVRSandbox;
import org.treblereel.gwt.three4g.demo.client.local.examples.webaudio.WebAudioOrientation;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.CanvasCameraOrthographicPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.Css2dLabelPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.Css3dOrthographicPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.DayDreamPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.MainPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.MiscAnimationGroupsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.Presenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.RollercoasterPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.ViveDraggingPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.VivePaintPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.VivePresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.ViveSculptPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebAudioOrientationPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebGlAnimationKeyframesJsonPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebGlCameraPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebVRCubesPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebVRPanoramaPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebVRSandboxPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglAnimationScenePresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglCameraArrayPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglClippingIntersectionPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglClippingPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglDecalsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglFramebufferTexturePresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometriesParametricPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometriesPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryColorsJsonPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryColorsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryCubePresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryDynamicPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryExtrudeShapesPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryExtrudeSplinesPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryHierarchy2Presenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryHierarchyPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryNormalsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryShapesPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryTeapotPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryTerrainFogPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryTerrainPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryTerrainRaycastPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryTextShapesPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglHdrPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglInteractiveBuffergeometryPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglInteractiveCubesGpuPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglInteractiveCubesPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglInteractiveDraggableCubesPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglInteractivePointsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLensflaresPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLightsHemispherePresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLightsPhysicalPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLightsPointlightsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLightsPointlightsPresenter2;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLightsSpotlightPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLightsSpotlightsPresenter;
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
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsCubemapBallsReflectionPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsCubemapBallsRefractionPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsCubemapDynamic2Presenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsCubemapPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsCubemapRefractionPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsDisplacementmapPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsEnvmapsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsGrassPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsLightmapPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsTextureAnisotropyPresent;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsTextureCanvasPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsTextureFiltersPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsTextureManualmipmapPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsTexturePartialupdatePresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsTextureRotationPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsTransparencyPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsVariationsBasicPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsVariationsLambertPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsVariationsPhongPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsVariationsPhysicalPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsVariationsStandardPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsVariationsToonPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglMaterialsWireframePresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglModifierSimplifierPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglModifierTessellationPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglPerformancePresenter;
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
    private WebglPerformancePresenter webglPerformancePresenter = GWT.create(WebglPerformancePresenter.class);
    private WebAudioOrientationPresenter webAudioOrientationPresenter = GWT.create(WebAudioOrientationPresenter.class);
    private WebglClippingIntersectionPresenter webglClippingIntersectionPresenter = GWT.create(WebglClippingIntersectionPresenter.class);
    private Css2dLabelPresenter css2dLabelPresenter = GWT.create(Css2dLabelPresenter.class);
    private Css3dOrthographicPresenter css3dOrthographicPresenter = GWT.create(Css3dOrthographicPresenter.class);
    private MiscAnimationGroupsPresenter miscAnimationGroupsPresenter = GWT.create(MiscAnimationGroupsPresenter.class);
    private WebglGeometryNormalsPresenter webglGeometryNormalsPresenter = GWT.create(WebglGeometryNormalsPresenter.class);
    private WebglGeometryShapesPresenter webglGeometryShapesPresenter = GWT.create(WebglGeometryShapesPresenter.class);
    private WebglGeometryColorsPresenter webglGeometryColorsPresenter = GWT.create(WebglGeometryColorsPresenter.class);
    private WebglGeometryColorsJsonPresenter webglGeometryColorsJsonPresenter = GWT.create(WebglGeometryColorsJsonPresenter.class);
    private WebglGeometryTeapotPresenter webglGeometryTeapotPresenter = GWT.create(WebglGeometryTeapotPresenter.class);
    private WebglInteractiveCubesPresenter webglInteractiveCubesPresenter = GWT.create(WebglInteractiveCubesPresenter.class);
    private WebglInteractiveCubesGpuPresenter webglInteractiveCubesGpuPresenter = GWT.create(WebglInteractiveCubesGpuPresenter.class);
    private WebglGeometryExtrudeShapesPresenter webglGeometryExtrudeShapesPresenter = GWT.create(WebglGeometryExtrudeShapesPresenter.class);
    private WebglGeometryExtrudeSplinesPresenter webglGeometryExtrudeSplinesPresenter = GWT.create(WebglGeometryExtrudeSplinesPresenter.class);
    private WebglGeometriesPresenter webglGeometriesPresenter = GWT.create(WebglGeometriesPresenter.class);
    private WebglGeometryHierarchyPresenter webglGeometryHierarchyPresenter = GWT.create(WebglGeometryHierarchyPresenter.class);
    private WebglGeometryHierarchy2Presenter webglGeometryHierarchy2Presenter = GWT.create(WebglGeometryHierarchy2Presenter.class);
    private WebglGeometryTextShapesPresenter webglGeometryTextShapesPresenter = GWT.create(WebglGeometryTextShapesPresenter.class);
    private WebglHdrPresenter webglHdrPresenter = GWT.create(WebglHdrPresenter.class);
    private WebglLightsHemispherePresenter webglLightsHemispherePresenter = GWT.create(WebglLightsHemispherePresenter.class);
    private WebglLightsPhysicalPresenter webglLightsPhysicalPresenter = GWT.create(WebglLightsPhysicalPresenter.class);
    private WebglLightsPointlightsPresenter webglLightsPointlightsPresenter = GWT.create(WebglLightsPointlightsPresenter.class);
    private WebglLightsPointlightsPresenter2 webglLightsPointlightsPresenter2 = GWT.create(WebglLightsPointlightsPresenter2.class);
    private WebglLightsSpotlightPresenter webglLightsSpotlightPresenter = GWT.create(WebglLightsSpotlightPresenter.class);
    private WebglLightsSpotlightsPresenter webglLightsSpotlightsPresenter = GWT.create(WebglLightsSpotlightsPresenter.class);
    private WebglMaterialsCubemapPresenter webglMaterialsCubemapPresenter = GWT.create(WebglMaterialsCubemapPresenter.class);
    private WebglMaterialsCubemapBallsReflectionPresenter webglMaterialsCubemapBallsReflectionPresenter = GWT.create(WebglMaterialsCubemapBallsReflectionPresenter.class);
    private WebglMaterialsCubemapBallsRefractionPresenter webglMaterialsCubemapBallsRefractionPresenter = GWT.create(WebglMaterialsCubemapBallsRefractionPresenter.class);
    private WebglMaterialsCubemapDynamic2Presenter webglMaterialsCubemapDynamic2Presenter = GWT.create(WebglMaterialsCubemapDynamic2Presenter.class);
    private WebglMaterialsCubemapRefractionPresenter webglMaterialsCubemapRefractionPresenter = GWT.create(WebglMaterialsCubemapRefractionPresenter.class);
    private WebglMaterialsDisplacementmapPresenter webglMaterialsDisplacementmapPresenter = GWT.create(WebglMaterialsDisplacementmapPresenter.class);
    private WebglMaterialsEnvmapsPresenter webglMaterialsEnvmapsPresenter = GWT.create(WebglMaterialsEnvmapsPresenter.class);
    private WebglMaterialsGrassPresenter webglMaterialsGrassPresenter = GWT.create(WebglMaterialsGrassPresenter.class);
    private WebglMaterialsLightmapPresenter webglMaterialsLightmapPresenter = GWT.create(WebglMaterialsLightmapPresenter.class);
    private WebglMaterialsTextureAnisotropyPresent webglMaterialsTextureAnisotropyPresent = GWT.create(WebglMaterialsTextureAnisotropyPresent.class);
    private WebglMaterialsTextureCanvasPresenter webglMaterialsTextureCanvasPresenter = GWT.create(WebglMaterialsTextureCanvasPresenter.class);
    private WebglMaterialsTextureFiltersPresenter webglMaterialsTextureFiltersPresenter = GWT.create(WebglMaterialsTextureFiltersPresenter.class);
    private WebglMaterialsTextureManualmipmapPresenter webglMaterialsTextureManualmipmapPresenter = GWT.create(WebglMaterialsTextureManualmipmapPresenter.class);
    private WebglMaterialsTexturePartialupdatePresenter webglMaterialsTexturePartialupdatePresenter = GWT.create(WebglMaterialsTexturePartialupdatePresenter.class);
    private WebglMaterialsTextureRotationPresenter webglMaterialsTextureRotationPresenter = GWT.create(WebglMaterialsTextureRotationPresenter.class);
    private WebglMaterialsTransparencyPresenter webglMaterialsTransparencyPresenter = GWT.create(WebglMaterialsTransparencyPresenter.class);
    private WebglMaterialsVariationsBasicPresenter webglMaterialsVariationsBasicPresenter = GWT.create(WebglMaterialsVariationsBasicPresenter.class);
    private WebglMaterialsVariationsLambertPresenter webglMaterialsVariationsLambertPresenter = GWT.create(WebglMaterialsVariationsLambertPresenter.class);
    private WebglMaterialsVariationsPhongPresenter webglMaterialsVariationsPhongPresenter = GWT.create(WebglMaterialsVariationsPhongPresenter.class);
    private WebglMaterialsVariationsStandardPresenter webglMaterialsVariationsStandardPresenter = GWT.create(WebglMaterialsVariationsStandardPresenter.class);
    private WebglMaterialsVariationsPhysicalPresenter webglMaterialsVariationsPhysicalPresenter = GWT.create(WebglMaterialsVariationsPhysicalPresenter.class);
    private WebglMaterialsVariationsToonPresenter webglMaterialsVariationsToonPresenter = GWT.create(WebglMaterialsVariationsToonPresenter.class);
    private WebglMaterialsWireframePresenter webglMaterialsWireframePresenter = GWT.create(WebglMaterialsWireframePresenter.class);
    private WebglModifierSimplifierPresenter webglModifierSimplifierPresenter = GWT.create(WebglModifierSimplifierPresenter.class);
    private WebglModifierTessellationPresenter webglModifierTessellationPresenter = GWT.create(WebglModifierTessellationPresenter.class);

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
        eventBus.addHandler(WebglPerformance.TYPE, event -> History.newItem(WebglPerformance.class.getSimpleName()));
        eventBus.addHandler(WebAudioOrientation.TYPE, event -> History.newItem(WebAudioOrientation.class.getSimpleName()));
        eventBus.addHandler(WebglClippingIntersection.TYPE, event -> History.newItem(WebglClippingIntersection.class.getSimpleName()));
        eventBus.addHandler(Css2dLabel.TYPE, event -> History.newItem(Css2dLabel.class.getSimpleName()));
        eventBus.addHandler(Css3dOrthographic.TYPE, event -> History.newItem(Css3dOrthographic.class.getSimpleName()));
        eventBus.addHandler(MiscAnimationGroups.TYPE, event -> History.newItem(MiscAnimationGroups.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryNormals.TYPE, event -> History.newItem(WebglGeometryNormals.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryShapes.TYPE, event -> History.newItem(WebglGeometryShapes.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryColors.TYPE, event -> History.newItem(WebglGeometryColors.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryColorsJson.TYPE, event -> History.newItem(WebglGeometryColorsJson.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryTeapot.TYPE, event -> History.newItem(WebglGeometryTeapot.class.getSimpleName()));
        eventBus.addHandler(WebglInteractiveCubes.TYPE, event -> History.newItem(WebglInteractiveCubes.class.getSimpleName()));
        eventBus.addHandler(WebglInteractiveCubesGpu.TYPE, event -> History.newItem(WebglInteractiveCubesGpu.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryExtrudeShapes.TYPE, event -> History.newItem(WebglGeometryExtrudeShapes.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryExtrudeSplines.TYPE, event -> History.newItem(WebglGeometryExtrudeSplines.class.getSimpleName()));
        eventBus.addHandler(WebglGeometries.TYPE, event -> History.newItem(WebglGeometries.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryHierarchy.TYPE, event -> History.newItem(WebglGeometryHierarchy.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryHierarchy2.TYPE, event -> History.newItem(WebglGeometryHierarchy2.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryTextShapes.TYPE, event -> History.newItem(WebglGeometryTextShapes.class.getSimpleName()));
        eventBus.addHandler(WebglHdr.TYPE, event -> History.newItem(WebglHdr.class.getSimpleName()));
        eventBus.addHandler(WebglLightsHemisphere.TYPE, event -> History.newItem(WebglLightsHemisphere.class.getSimpleName()));
        eventBus.addHandler(WebglLightsPhysical.TYPE, event -> History.newItem(WebglLightsPhysical.class.getSimpleName()));
        eventBus.addHandler(WebglLightsPointlights.TYPE, event -> History.newItem(WebglLightsPointlights.class.getSimpleName()));
        eventBus.addHandler(WebglLightsPointlights2.TYPE, event -> History.newItem(WebglLightsPointlights2.class.getSimpleName()));
        eventBus.addHandler(WebglLightsSpotlight.TYPE, event -> History.newItem(WebglLightsSpotlight.class.getSimpleName()));
        eventBus.addHandler(WebglLightsSpotlights.TYPE, event -> History.newItem(WebglLightsSpotlights.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsCubemap.TYPE, event -> History.newItem(WebglMaterialsCubemap.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsCubemapBallsReflection.TYPE, event -> History.newItem(WebglMaterialsCubemapBallsReflection.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsCubemapBallsRefraction.TYPE, event -> History.newItem(WebglMaterialsCubemapBallsRefraction.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsCubemapDynamic2.TYPE, event -> History.newItem(WebglMaterialsCubemapDynamic2.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsCubemapRefraction.TYPE, event -> History.newItem(WebglMaterialsCubemapRefraction.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsDisplacementmap.TYPE, event -> History.newItem(WebglMaterialsDisplacementmap.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsEnvmaps.TYPE, event -> History.newItem(WebglMaterialsEnvmaps.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsGrass.TYPE, event -> History.newItem(WebglMaterialsGrass.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsLightmap.TYPE, event -> History.newItem(WebglMaterialsLightmap.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsTextureAnisotropy.TYPE, event -> History.newItem(WebglMaterialsTextureAnisotropy.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsTextureCanvas.TYPE, event -> History.newItem(WebglMaterialsTextureCanvas.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsTextureFilters.TYPE, event -> History.newItem(WebglMaterialsTextureFilters.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsTextureManualmipmap.TYPE, event -> History.newItem(WebglMaterialsTextureManualmipmap.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsTexturePartialupdate.TYPE, event -> History.newItem(WebglMaterialsTexturePartialupdate.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsTextureRotation.TYPE, event -> History.newItem(WebglMaterialsTextureRotation.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsTransparency.TYPE, event -> History.newItem(WebglMaterialsTransparency.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsVariationsBasic.TYPE, event -> History.newItem(WebglMaterialsVariationsBasic.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsVariationsLambert.TYPE, event -> History.newItem(WebglMaterialsVariationsLambert.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsVariationsPhong.TYPE, event -> History.newItem(WebglMaterialsVariationsPhong.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsVariationsStandard.TYPE, event -> History.newItem(WebglMaterialsVariationsStandard.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsVariationsPhysical.TYPE, event -> History.newItem(WebglMaterialsVariationsPhysical.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsVariationsToon.TYPE, event -> History.newItem(WebglMaterialsVariationsToon.class.getSimpleName()));
        eventBus.addHandler(WebglMaterialsWireframe.TYPE, event -> History.newItem(WebglMaterialsWireframe.class.getSimpleName()));
        eventBus.addHandler(WebglModifierSimplifier.TYPE, event -> History.newItem(WebglModifierSimplifier.class.getSimpleName()));
        eventBus.addHandler(WebglModifierTessellation.TYPE, event -> History.newItem(WebglModifierTessellation.class.getSimpleName()));


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
            } else if (token.equals(WebglPerformance.class.getSimpleName())) {
                webglPerformancePresenter.dispatch(container);
            } else if (token.equals(WebAudioOrientation.class.getSimpleName())) {
                webAudioOrientationPresenter.dispatch(container);
            } else if (token.equals(WebglClippingIntersection.class.getSimpleName())) {
                webglClippingIntersectionPresenter.dispatch(container);
            } else if (token.equals(Css2dLabel.class.getSimpleName())) {
                css2dLabelPresenter.dispatch(container);
            } else if (token.equals(Css3dOrthographic.class.getSimpleName())) {
                css3dOrthographicPresenter.dispatch(container);
            } else if (token.equals(MiscAnimationGroups.class.getSimpleName())) {
                miscAnimationGroupsPresenter.dispatch(container);
            } else if (token.equals(WebglGeometryNormals.class.getSimpleName())) {
                webglGeometryNormalsPresenter.dispatch(container);
            } else if (token.equals(WebglGeometryShapes.class.getSimpleName())) {
                webglGeometryShapesPresenter.dispatch(container);
            } else if (token.equals(WebglGeometryColors.class.getSimpleName())) {
                webglGeometryColorsPresenter.dispatch(container);
            } else if (token.equals(WebglGeometryColorsJson.class.getSimpleName())) {
                webglGeometryColorsJsonPresenter.dispatch(container);
            } else if (token.equals(WebglGeometryTeapot.class.getSimpleName())) {
                webglGeometryTeapotPresenter.dispatch(container);
            } else if (token.equals(WebglInteractiveCubes.class.getSimpleName())) {
                webglInteractiveCubesPresenter.dispatch(container);
            } else if (token.equals(WebglInteractiveCubesGpu.class.getSimpleName())) {
                webglInteractiveCubesGpuPresenter.dispatch(container);
            } else if (token.equals(WebglGeometryExtrudeShapes.class.getSimpleName())) {
                webglGeometryExtrudeShapesPresenter.dispatch(container);
            } else if (token.equals(WebglGeometryExtrudeSplines.class.getSimpleName())) {
                webglGeometryExtrudeSplinesPresenter.dispatch(container);
            } else if (token.equals(WebglGeometries.class.getSimpleName())) {
                webglGeometriesPresenter.dispatch(container);
            } else if (token.equals(WebglGeometryHierarchy.class.getSimpleName())) {
                webglGeometryHierarchyPresenter.dispatch(container);
            } else if (token.equals(WebglGeometryHierarchy2.class.getSimpleName())) {
                webglGeometryHierarchy2Presenter.dispatch(container);
            } else if (token.equals(WebglGeometryTextShapes.class.getSimpleName())) {
                webglGeometryTextShapesPresenter.dispatch(container);
            } else if (token.equals(WebglHdr.class.getSimpleName())) {
                webglHdrPresenter.dispatch(container);
            } else if (token.equals(WebglLightsHemisphere.class.getSimpleName())) {
                webglLightsHemispherePresenter.dispatch(container);
            } else if (token.equals(WebglLightsPhysical.class.getSimpleName())) {
                webglLightsPhysicalPresenter.dispatch(container);
            } else if (token.equals(WebglLightsPointlights.class.getSimpleName())) {
                webglLightsPointlightsPresenter.dispatch(container);
            } else if (token.equals(WebglLightsPointlights2.class.getSimpleName())) {
                webglLightsPointlightsPresenter2.dispatch(container);
            } else if (token.equals(WebglLightsSpotlight.class.getSimpleName())) {
                webglLightsSpotlightPresenter.dispatch(container);
            } else if (token.equals(WebglLightsSpotlights.class.getSimpleName())) {
                webglLightsSpotlightsPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsCubemap.class.getSimpleName())) {
                webglMaterialsCubemapPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsCubemapBallsReflection.class.getSimpleName())) {
                webglMaterialsCubemapBallsReflectionPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsCubemapBallsRefraction.class.getSimpleName())) {
                webglMaterialsCubemapBallsRefractionPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsCubemapDynamic2.class.getSimpleName())) {
                webglMaterialsCubemapDynamic2Presenter.dispatch(container);
            } else if (token.equals(WebglMaterialsCubemapRefraction.class.getSimpleName())) {
                webglMaterialsCubemapRefractionPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsDisplacementmap.class.getSimpleName())) {
                webglMaterialsDisplacementmapPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsEnvmaps.class.getSimpleName())) {
                webglMaterialsEnvmapsPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsGrass.class.getSimpleName())) {
                webglMaterialsGrassPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsLightmap.class.getSimpleName())) {
                webglMaterialsLightmapPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsTextureAnisotropy.class.getSimpleName())) {
                webglMaterialsTextureAnisotropyPresent.dispatch(container);
            } else if (token.equals(WebglMaterialsTextureCanvas.class.getSimpleName())) {
                webglMaterialsTextureCanvasPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsTextureFilters.class.getSimpleName())) {
                webglMaterialsTextureFiltersPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsTextureManualmipmap.class.getSimpleName())) {
                webglMaterialsTextureManualmipmapPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsTexturePartialupdate.class.getSimpleName())) {
                webglMaterialsTexturePartialupdatePresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsTextureRotation.class.getSimpleName())) {
                webglMaterialsTextureRotationPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsTransparency.class.getSimpleName())) {
                webglMaterialsTransparencyPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsVariationsBasic.class.getSimpleName())) {
                webglMaterialsVariationsBasicPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsVariationsLambert.class.getSimpleName())) {
                webglMaterialsVariationsLambertPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsVariationsPhong.class.getSimpleName())) {
                webglMaterialsVariationsPhongPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsVariationsStandard.class.getSimpleName())) {
                webglMaterialsVariationsStandardPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsVariationsPhysical.class.getSimpleName())) {
                webglMaterialsVariationsPhysicalPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsVariationsToon.class.getSimpleName())) {
                webglMaterialsVariationsToonPresenter.dispatch(container);
            } else if (token.equals(WebglMaterialsWireframe.class.getSimpleName())) {
                webglMaterialsWireframePresenter.dispatch(container);
            } else if (token.equals(WebglModifierSimplifier.class.getSimpleName())) {
                webglModifierSimplifierPresenter.dispatch(container);
            }else if (token.equals(WebglModifierTessellation.class.getSimpleName())) {
                webglModifierTessellationPresenter.dispatch(container);
            }
        }
    }
}