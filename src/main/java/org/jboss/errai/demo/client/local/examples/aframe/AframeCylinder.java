package org.jboss.errai.demo.client.local.examples.aframe;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import org.jboss.errai.common.client.api.annotations.Element;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 4/16/18.
 */
@Element("a-cylinder")
@JsType(isNative = true, name = "HTMLElement", namespace = JsPackage.GLOBAL)
public abstract class AframeCylinder extends Object3D{

    @JsOverlay
    final double getRadius(){
        return Double.valueOf(getAttribute("radius"));
    }

    @JsOverlay
    final void setRadius(double radius){
        setAttribute("radius", radius);

    }

    @JsOverlay
    final double getHeight(){
        return Double.valueOf(getAttribute("height"));
    }

    @JsOverlay
    final void setHeight(double height){
        setAttribute("height", height);

    }

}
