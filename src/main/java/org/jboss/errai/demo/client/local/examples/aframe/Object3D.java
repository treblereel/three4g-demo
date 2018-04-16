package org.jboss.errai.demo.client.local.examples.aframe;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 4/16/18.
 */
@JsType(isNative = true)
public abstract class Object3D extends HTMLElement {

    @JsOverlay
    final String getPosition(){
        return getAttribute("position");
    }

    @JsOverlay
    final void setPosition(String position){
        setAttribute("position", position);
    }

    @JsOverlay
    final String getRotation(){
        return getAttribute("rotation");
    }

    @JsOverlay
    final void setRotation(String rotation){
        setAttribute("rotation", rotation);

    }

    @JsOverlay
    final String getColor(){
        return getAttribute("color");
    }

    @JsOverlay
    final void setColor(String color){
        setAttribute("color", color);

    }



}
