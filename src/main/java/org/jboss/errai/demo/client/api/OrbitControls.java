package org.jboss.errai.demo.client.api;

import elemental2.dom.HTMLElement;
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

    public Number maxPolarAngle;
    public Number minDistance;
    public Number maxDistance;

    @JsConstructor
    public OrbitControls(Camera camera){

    }

    @JsConstructor
    public OrbitControls(Camera camera, HTMLElement domElement){

    }

}
