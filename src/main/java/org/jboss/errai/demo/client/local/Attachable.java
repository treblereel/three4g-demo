package org.jboss.errai.demo.client.local;

import com.google.gwt.core.client.ScriptInjector;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLIFrameElement;
import elemental2.dom.Window;
import org.jboss.errai.ioc.client.api.LoadAsync;
import org.slf4j.Logger;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.objects.Mesh;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.scenes.Scene;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/13/18.
 */
@LoadAsync
@ApplicationScoped
public abstract class Attachable {

    @Inject
    Logger logger;

    @Inject
    protected ViewSrcButton viewSrcButton;

    @Inject
    protected InfoDiv info;

    protected Window window = DomGlobal.window;
    protected WebGLRenderer webGLRenderer;
    protected Scene scene;
    protected PerspectiveCamera camera;
    protected Mesh mesh;

    protected float aspect = new Float((getWidth() / getHeight()));

    protected abstract void doAttachScene();

    protected abstract void doAttachInfo();

    public void detach() {

    }

    public void attach() {
        doAttachScene();
        doAttachInfo();
        doAttachLink();
    }

    private void doAttachLink() {
        viewSrcButton.setLink(getClass().getCanonicalName());
    }

    private void clearContainer(HTMLIFrameElement container) {
        while (container.hasChildNodes()) {
            container.removeChild(container.firstChild);
        }
    }

    public double getWidth() {
        return window.innerWidth - 310;
    }

    public double getHeight() {
        return window.innerHeight;
    }

    //TODO
    public void onWindowResize() {
        if (webGLRenderer.domElement.parentNode != null && camera != null) {
            camera.aspect = new Float(getWidth() / getHeight());
            camera.updateProjectionMatrix();
            webGLRenderer.setSize(getWidth(), getHeight());
        }
    }

    public void setupWebGLRenderer(WebGLRenderer webGLRenderer) {
        webGLRenderer.domElement.id = "viewer";
        webGLRenderer.setSize(getWidth(), getHeight());
    }

    protected void loadJavaScript(String script) {
        ScriptInjector.fromString(script).setWindow(ScriptInjector.TOP_WINDOW).inject();

    }

}
