package org.jboss.errai.demo.client.local;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import elemental2.dom.DomGlobal;
import elemental2.dom.Event;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLIFrameElement;
import elemental2.dom.Window;
import org.jboss.errai.demo.client.local.utils.WebGLRendererProducer;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.scenes.Scene;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/13/18.
 */
public abstract class Attachable {

    protected Window window = DomGlobal.window;
    protected WebGLRenderer webGLRenderer = WebGLRendererProducer.getRenderer();
    protected Scene scene;
    protected PerspectiveCamera camera;
    protected Mesh mesh;

    protected HTMLDivElement root = (HTMLDivElement) DomGlobal.document.createElement("div");


    protected float aspect = new Float((getWidth() / getHeight()));

    protected abstract void doAttachScene();

    protected abstract void doAttachInfo();


    public void attach() {
        doAttachScene();
        doAttachInfo();
        doAttachLink();
        window.addEventListener("resize", evt -> onWindowResize(), false);
    }

    private void doAttachLink() {
        AppSetup.viewSrcButton.setLink(getClass().getCanonicalName());
    }

    private void clearContainer(HTMLIFrameElement container) {
        while (container.hasChildNodes()) {
            container.removeChild(container.firstChild);
        }
    }

    public double getWidth() {
        return window.innerWidth * 0.8;
    }

    public double getHeight() {
        return window.innerHeight;
    }

    //TODO
    public void onWindowResize() {
        if (root.parentNode != null && camera != null) {
            camera.aspect = new Float(getWidth() / getHeight());
            camera.updateProjectionMatrix();
            webGLRenderer.setSize(getWidth(), getHeight());
        }
    }

    public void setupWebGLRenderer(WebGLRenderer webGLRenderer) {
/*
        WebGLRendererParameters webGLRendererParameters = new WebGLRendererParameters();
        webGLRendererParameters.antialias = true;

        webGLRenderer = new WebGLRenderer(webGLRendererParameters);*/
        webGLRenderer.domElement.id = "viewer";
        webGLRenderer.setSize(getWidth(), getHeight());
    }

    public static void loadJavaScript(String script) {
        ScriptInjector.fromString(script).setWindow(ScriptInjector.TOP_WINDOW).inject();

    }

    public void attach(HTMLDivElement container) {
        for (int i = 0; i < container.childElementCount; i++) {
            container.removeChild(container.childNodes.item(i));
        }
        attach();
        container.appendChild(root);
    }

}
