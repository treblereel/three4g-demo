package org.jboss.errai.demo.client.local;

import jsinterop.annotations.JsFunction;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 *         Created by treblereel on 12/8/17.
 */
@JsFunction
@FunctionalInterface
public interface Click {

    void click();
}
