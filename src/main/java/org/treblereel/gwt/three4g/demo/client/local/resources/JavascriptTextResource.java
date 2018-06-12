package org.treblereel.gwt.three4g.demo.client.local.resources;

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

    @Source("js/CurveExtras.js")
    TextResource getCurveExtras();

    @Source("js/ParametricGeometries.js")
    TextResource getParametricGeometries();

    @Source("js/GPUComputationRenderer.js")
    TextResource getGPUComputationRenderer();

    @Source("js/birdGeometry.js")
    TextResource getBirdGeometry();

    @Source("js/stats.min.js")
    TextResource getStatsMin();

    @Source("js/RollerCoaster.js")
    TextResource getRollercoaster();

    @Source("js/PaintViveController.js")
    TextResource getPaintViveController();

    @Source("js/MarchingCubes.js")
    TextResource getMarchingCubes();

    @Source("js/TrackballControls.js")
    TextResource getTrackballControls();

    @Source("js/Tween.js")
    TextResource getTWEEN();
}
