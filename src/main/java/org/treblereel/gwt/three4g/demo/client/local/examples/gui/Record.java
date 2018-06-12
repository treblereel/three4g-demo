package org.treblereel.gwt.three4g.demo.client.local.examples.gui;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

import static jsinterop.annotations.JsPackage.GLOBAL;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 *         Created by treblereel on 12/7/17.
 */
@JsType(isNative = true, namespace = GLOBAL, name = "Object")
public class Record {

    String id;
    Double date;
    Double data;
    Boolean alive;
    String car;
    String speed;

    public Record() {

    }

}
