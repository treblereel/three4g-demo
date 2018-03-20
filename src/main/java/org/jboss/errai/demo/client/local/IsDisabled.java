package org.jboss.errai.demo.client.local;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 *         Created by treblereel on 10/2/17.
 */
@JsType(isNative = true)
public interface IsDisabled {

    @JsProperty
    boolean getDisabled();

    @JsProperty
    void setDisabled(boolean value);
}
