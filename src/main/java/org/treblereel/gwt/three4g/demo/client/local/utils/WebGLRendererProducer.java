package org.treblereel.gwt.three4g.demo.client.local.utils;

import org.treblereel.gwt.three4g.renderers.WebGLRenderer;
import org.treblereel.gwt.three4g.renderers.parameters.WebGLRendererParameters;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/1/18.
 */
public class WebGLRendererProducer {
    private static WebGLRenderer webGLRenderer;

    private WebGLRendererProducer() {

    }

    public static WebGLRenderer getRenderer() {
        if (webGLRenderer == null) {
            WebGLRendererParameters webGLRendererParameters = new WebGLRendererParameters();
            webGLRendererParameters.antialias = true;
            webGLRenderer = new WebGLRenderer(webGLRendererParameters);
        }else{

            webGLRenderer.clear();
          //  webGLRenderer.forceContextLoss();
        }
        webGLRenderer.autoClear = true;
        return webGLRenderer;
    }
}
