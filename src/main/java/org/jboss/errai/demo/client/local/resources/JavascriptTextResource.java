package org.jboss.errai.demo.client.local.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/26/18.
 */
public interface JavascriptTextResource extends ClientBundle {

    JavascriptTextResource IMPL = GWT.create(JavascriptTextResource.class);


    @Source("js/FirstPersonControls.js")
    TextResource getFirstPersonControls();

    @Source("js/OrbitControls.js")
    TextResource getOrbitControls();


}
