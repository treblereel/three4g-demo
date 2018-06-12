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
import org.treblereel.gwt.three4g.demo.client.local.examples.camera.WebGlCamera;
import org.treblereel.gwt.three4g.demo.client.local.examples.camera.WebglCameraArray;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometriesParametric;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryCube;
import org.treblereel.gwt.three4g.demo.client.local.examples.geometry.WebglGeometryDynamic;
import org.treblereel.gwt.three4g.demo.client.local.examples.interactive.WebglInteractiveBuffergeometry;
import org.treblereel.gwt.three4g.demo.client.local.examples.interactive.WebglInteractivePoints;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoader3ds;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderBabylon;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderCollada;
import org.treblereel.gwt.three4g.demo.client.local.examples.loaders.WebglLoaderColladaKinematics;
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
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.DayDream;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.Rollercoaster;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.Vive;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.ViveDragging;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.VivePaint;
import org.treblereel.gwt.three4g.demo.client.local.examples.vr.ViveSculpt;
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
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglAnimationScenePresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglCameraArrayPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometriesParametricPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryCubePresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglGeometryDynamicPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglInteractiveBuffergeometryPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglInteractivePointsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoader3dsPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderBabylonPresenter;
import org.treblereel.gwt.three4g.demo.client.local.mvc.presenter.WebglLoaderColladaKinematicsPresenter;
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


    }

    public void dispatch(final HTMLDivElement container) {
        this.container = container;
        if ("".equals(History.getToken())) {
            History.newItem(MainView.class.getSimpleName());
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
            }else if (token.equals(WebglCameraArray.class.getSimpleName())) {
                webglCameraArrayPresenter.dispatch(container);
            }else if (token.equals(WebglGeometriesParametric.class.getSimpleName())) {
                webglGeometriesParametricPresenter.dispatch(container);
            }else if (token.equals(WebglGeometryCube.class.getSimpleName())) {
                webglGeometryCubePresenter.dispatch(container);
            }else if (token.equals(WebglGeometryDynamic.class.getSimpleName())) {
                webglGeometryDynamicPresenter.dispatch(container);
            }else if (token.equals(DayDream.class.getSimpleName())) {
                dayDreamPresenter.dispatch(container);
            }else if (token.equals(Rollercoaster.class.getSimpleName())) {
                rollercoasterPresenter.dispatch(container);
            }else if (token.equals(Vive.class.getSimpleName())) {
                vivePresenter.dispatch(container);
            }else if (token.equals(ViveDragging.class.getSimpleName())) {
                viveDraggingPresenter.dispatch(container);
            }else if (token.equals(VivePaint.class.getSimpleName())) {
                vivePaintPresenter.dispatch(container);
            }else if (token.equals(ViveSculpt.class.getSimpleName())) {
                viveSculptPresenter.dispatch(container);
            }else if (token.equals(WebglInteractivePoints.class.getSimpleName())) {
                webglInteractivePointsPresenter.dispatch(container);
            }else if (token.equals(WebglInteractiveBuffergeometry.class.getSimpleName())) {
                webglInteractiveBuffergeometryPresenter.dispatch(container);
            }else if (token.equals(WebglLoaderJsonBlender.class.getSimpleName())) {
                webglLoaderJsonBlenderPresenter.dispatch(container);
            }else if (token.equals(WebglLoader3ds.class.getSimpleName())) {
                webglLoader3dsPresenter.dispatch(container);
            }else if (token.equals(WebglLoaderCollada.class.getSimpleName())) {
                webglLoaderColladaPresenter.dispatch(container);
            }else if (token.equals(WebglLoaderColladaSkinning.class.getSimpleName())) {
                webglLoaderColladaSkinningPresenter.dispatch(container);
            }else if (token.equals(WebglLoaderDraco.class.getSimpleName())) {
                webglLoaderDracoPresenter.dispatch(container);
            }else if (token.equals(WebglLoaderFbx.class.getSimpleName())) {
                webglLoaderFbxPresenter.dispatch(container);
            }else if (token.equals(WebglLoaderGltf.class.getSimpleName())) {
                webglLoaderGltfPresenter.dispatch(container);
            }else if (token.equals(WebglLoaderBabylon.class.getSimpleName())) {
                webglLoaderBabylonPresenter.dispatch(container);
            }else if (token.equals(WebglLoaderObj.class.getSimpleName())) {
                webglLoaderObjPresenter.dispatch(container);
            }else if (token.equals(WebglLoaderObj2.class.getSimpleName())) {
                webglLoaderObj2Presenter.dispatch(container);
            }else if (token.equals(WebglLoaderPcd.class.getSimpleName())) {
                webglLoaderPcdPresenter.dispatch(container);
            }else if (token.equals(WebglLoaderPdb.class.getSimpleName())) {
                webglLoaderPdbPresenter.dispatch(container);
            }else if (token.equals(WebglLoaderPrwm.class.getSimpleName())) {
                webglLoaderPrwmPresenter.dispatch(container);
            }else if (token.equals(WebglLoaderTextureTga.class.getSimpleName())) {
                webglLoaderTextureTgaPresenter.dispatch(container);
            }
        }
    }
}