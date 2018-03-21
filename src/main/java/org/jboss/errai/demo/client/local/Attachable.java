package org.jboss.errai.demo.client.local;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLIFrameElement;
import elemental2.dom.Window;
import org.slf4j.Logger;
import org.treblereel.gwt.three4g.cameras.PerspectiveCamera;
import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.scenes.Scene;

import javax.inject.Inject;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/13/18.
 */
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

    protected float aspect = new Float((getWidth() / getHeight()));

    protected abstract void doAttachScene();

    protected abstract void doAttachInfo();


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
        if (webGLRenderer.domElement.parentNode != null) {
            camera.aspect = new Float(getWidth() / getHeight());
            camera.updateProjectionMatrix();
            webGLRenderer.setSize(getWidth(), getHeight());
        }
    }

    public void setupWebGLRenderer(WebGLRenderer webGLRenderer) {
        webGLRenderer.domElement.id = "viewer";
        webGLRenderer.setSize(getWidth(), getHeight());
    }

}
