package org.jboss.errai.demo.client.local.examples.animation;

import com.google.gwt.animation.client.AnimationScheduler;
import elemental2.dom.HTMLDivElement;
import jsinterop.base.Js;
import jsinterop.base.JsArrayLike;
import jsinterop.base.JsPropertyMap;
import org.jboss.errai.demo.client.local.Attachable;
import org.jboss.errai.demo.client.local.examples.geometry.css.GeometryCssClientBundle;
import org.jboss.errai.ioc.client.api.LoadAsync;
import org.slf4j.Logger;
import org.treblereel.gwt.three4g.animation.AnimationClip;
import org.treblereel.gwt.three4g.animation.AnimationMixer;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.core.Clock;
import org.treblereel.gwt.three4g.core.Color;
import org.treblereel.gwt.three4g.helpers.GridHelper;
import org.treblereel.gwt.three4g.lights.AmbientLight;
import org.treblereel.gwt.three4g.lights.PointLight;
import org.treblereel.gwt.three4g.loaders.ObjectLoader;
import org.treblereel.gwt.three4g.loaders.OnLoadCallback;
import org.treblereel.gwt.three4g.math.Vector3;
import org.treblereel.gwt.three4g.objects.Group;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.renderers.WebGLRendererParameters;
import org.treblereel.gwt.three4g.scenes.Scene;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/16/18.
 */
@LoadAsync
@ApplicationScoped
public class WebGlAnimationKeyframesJson extends Attachable {

    @Inject
    HTMLDivElement root;

    AnimationMixer mixer;

    @Inject
    Logger logger;

    String json = "json/pump/pump.json";

    Clock clock = new Clock();

    @PostConstruct
    public void init() {
        GeometryCssClientBundle.IMPL.webglAnimationScene().ensureInjected();

        WebGLRendererParameters webGLRendererParameters = new WebGLRendererParameters();
        webGLRendererParameters.antialias = true;
        webGLRenderer = new WebGLRenderer(webGLRendererParameters);
        setupWebGLRenderer(webGLRenderer);

        scene = new Scene();

        GridHelper grid = new GridHelper(20, 20, new Color(0x888888), new Color(0x888888));
        grid.position.set(0, -1.1f, 0);
        scene.add(grid);

        camera = new PerspectiveCamera(40, aspect, 1, 100);
        camera.position.set( -5.00f, 3.43f, 11.31f);
        camera.lookAt(new Vector3(-1.22f, 2.18f, 4.58f));
        scene.add(new AmbientLight(0x404040));

        PointLight pointLight = new PointLight(0xffffff, 1);
        pointLight.position.copy(camera.position);
        scene.add(pointLight);

        new ObjectLoader().load(json, new OnLoadCallback<Object>() {

            @Override
            public void onLoad(Object object) {
                scene.add((Group) object);
                JsPropertyMap<Object> javaScriptObject = Js.cast(object);
                JsArrayLike<AnimationClip> array = Js.cast(javaScriptObject.get("animations"));
                AnimationClip animationClip = array.getAt(0);
                mixer = new AnimationMixer((Group) object);
                mixer.clipAction(animationClip).play();
            }
        });


    }

    public void doAttachScene() {
        document.body.appendChild(webGLRenderer.domElement);
        onWindowResize();
        animate();
    }

    @Override
    protected void doAttachInfo() {
        info.show().setHrefToInfo("http://threejs.org").setTextContentToInfo("three.js").setTextToDesc(" webgl - animation - keyframes - json");
    }

    private void render() {
        if (mixer != null) {
            mixer.update(clock.getDelta());
            webGLRenderer.render(scene, camera);
        }
    }

    private void animate() {
        AnimationScheduler.get().requestAnimationFrame(timestamp -> {
            if (webGLRenderer.domElement != null) {
                render();
                animate();
            }
        });
    }
}
