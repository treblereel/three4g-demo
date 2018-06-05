package org.jboss.errai.demo.client.local.mvc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import elemental2.dom.HTMLDivElement;
import org.jboss.errai.demo.client.local.examples.animation.WebGlAnimationKeyframesJson;
import org.jboss.errai.demo.client.local.examples.animation.WebglAnimationScene;
import org.jboss.errai.demo.client.local.examples.camera.WebGlCamera;
import org.jboss.errai.demo.client.local.examples.camera.WebglCameraArray;
import org.jboss.errai.demo.client.local.examples.geometry.WebglGeometriesParametric;
import org.jboss.errai.demo.client.local.examples.geometry.WebglGeometryCube;
import org.jboss.errai.demo.client.local.examples.geometry.WebglGeometryDynamic;
import org.jboss.errai.demo.client.local.examples.vr.DayDream;
import org.jboss.errai.demo.client.local.examples.vr.Rollercoaster;
import org.jboss.errai.demo.client.local.examples.vr.Vive;
import org.jboss.errai.demo.client.local.examples.vr.ViveDragging;
import org.jboss.errai.demo.client.local.examples.vr.VivePaint;
import org.jboss.errai.demo.client.local.examples.vr.ViveSculpt;
import org.jboss.errai.demo.client.local.mvc.event.DayDreamEvent;
import org.jboss.errai.demo.client.local.mvc.event.MainEvent;
import org.jboss.errai.demo.client.local.mvc.event.RollercoasterEvent;
import org.jboss.errai.demo.client.local.mvc.event.ViveDraggingEvent;
import org.jboss.errai.demo.client.local.mvc.event.ViveEvent;
import org.jboss.errai.demo.client.local.mvc.event.VivePaintEvent;
import org.jboss.errai.demo.client.local.mvc.event.ViveSculptEvent;
import org.jboss.errai.demo.client.local.mvc.event.WebGlAnimationKeyframesJsonEvent;
import org.jboss.errai.demo.client.local.mvc.event.WebGlCameraEvent;
import org.jboss.errai.demo.client.local.mvc.event.WebglAnimationSceneEvent;
import org.jboss.errai.demo.client.local.mvc.event.WebglCameraArrayEvent;
import org.jboss.errai.demo.client.local.mvc.event.WebglGeometriesParametricEvent;
import org.jboss.errai.demo.client.local.mvc.event.WebglGeometryCubeEvent;
import org.jboss.errai.demo.client.local.mvc.event.WebglGeometryDynamicEvent;
import org.jboss.errai.demo.client.local.mvc.presenter.DayDreamPresenter;
import org.jboss.errai.demo.client.local.mvc.presenter.MainPresenter;
import org.jboss.errai.demo.client.local.mvc.presenter.Presenter;
import org.jboss.errai.demo.client.local.mvc.presenter.RollercoasterPresenter;
import org.jboss.errai.demo.client.local.mvc.presenter.ViveDraggingPresenter;
import org.jboss.errai.demo.client.local.mvc.presenter.VivePaintPresenter;
import org.jboss.errai.demo.client.local.mvc.presenter.VivePresenter;
import org.jboss.errai.demo.client.local.mvc.presenter.ViveSculptPresenter;
import org.jboss.errai.demo.client.local.mvc.presenter.WebGlAnimationKeyframesJsonPresenter;
import org.jboss.errai.demo.client.local.mvc.presenter.WebGlCameraPresenter;
import org.jboss.errai.demo.client.local.mvc.presenter.WebglAnimationScenePresenter;
import org.jboss.errai.demo.client.local.mvc.presenter.WebglCameraArrayPresenter;
import org.jboss.errai.demo.client.local.mvc.presenter.WebglGeometriesParametricPresenter;
import org.jboss.errai.demo.client.local.mvc.presenter.WebglGeometryCubePresenter;
import org.jboss.errai.demo.client.local.mvc.presenter.WebglGeometryDynamicPresenter;
import org.jboss.errai.demo.client.local.mvc.view.MainView;


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


    private HTMLDivElement container;


    public AppController() {
        bind();
    }

    public void bind() {
        History.addValueChangeHandler(this);
        eventBus.addHandler(MainEvent.TYPE, event -> MainView.class.getSimpleName());
        eventBus.addHandler(WebGlAnimationKeyframesJsonEvent.TYPE, event -> History.newItem(WebGlAnimationKeyframesJson.class.getSimpleName()));
        eventBus.addHandler(WebglAnimationSceneEvent.TYPE, event -> History.newItem(WebglAnimationScene.class.getSimpleName()));
        eventBus.addHandler(WebGlCameraEvent.TYPE, event -> History.newItem(WebGlCamera.class.getSimpleName()));
        eventBus.addHandler(WebglCameraArrayEvent.TYPE, event -> History.newItem(WebglCameraArray.class.getSimpleName()));
        eventBus.addHandler(WebglGeometriesParametricEvent.TYPE, event -> History.newItem(WebglGeometriesParametric.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryCubeEvent.TYPE, event -> History.newItem(WebglGeometryCube.class.getSimpleName()));
        eventBus.addHandler(WebglGeometryDynamicEvent.TYPE, event -> History.newItem(WebglGeometryDynamic.class.getSimpleName()));
        eventBus.addHandler(DayDreamEvent.TYPE, event -> History.newItem(DayDream.class.getSimpleName()));
        eventBus.addHandler(RollercoasterEvent.TYPE, event -> History.newItem(Rollercoaster.class.getSimpleName()));
        eventBus.addHandler(ViveEvent.TYPE, event -> History.newItem(Vive.class.getSimpleName()));
        eventBus.addHandler(ViveDraggingEvent.TYPE, event -> History.newItem(ViveDragging.class.getSimpleName()));
        eventBus.addHandler(VivePaintEvent.TYPE, event -> History.newItem(VivePaint.class.getSimpleName()));
        eventBus.addHandler(ViveSculptEvent.TYPE, event -> History.newItem(ViveSculpt.class.getSimpleName()));


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
            }
        }
    }
}