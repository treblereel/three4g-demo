package org.jboss.errai.demo.client.api;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsType;
import org.treblereel.gwt.three4g.cameras.Camera;
import org.treblereel.gwt.three4g.core.EventDispatcher;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/13/18.
 */
@JsType(isNative = true, namespace = "THREE")
public class OrbitControls extends EventDispatcher {

    @JsConstructor
    public OrbitControls(Camera camera){

    }

}
