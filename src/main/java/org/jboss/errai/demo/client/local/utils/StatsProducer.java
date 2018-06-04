package org.jboss.errai.demo.client.local.utils;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLCanvasElement;
import elemental2.dom.HTMLDivElement;
import org.jboss.errai.demo.client.api.Stats;
import org.jboss.errai.demo.client.local.resources.JavascriptTextResource;

import static org.jboss.errai.demo.client.local.Attachable.loadJavaScript;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 6/1/18.
 */
public class StatsProducer {
    private static Stats stats;

    private StatsProducer(){

    }

    public static Stats getStats(){
        if(stats == null){
            loadJavaScript(JavascriptTextResource.IMPL.getStatsMin().getText());
            stats = new Stats();
            HTMLDivElement container = (HTMLDivElement)DomGlobal.document.createElement("div");
            stats.dom.style.position = "inherit";
            container.style.bottom = "0";
            container.style.right = "0";
            container.appendChild(stats.dom);

            DomGlobal.document.body.appendChild(container);

        }
        return stats;
    }

}
