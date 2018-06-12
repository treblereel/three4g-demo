package org.treblereel.gwt.three4g.demo.client.local;

import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLDivElement;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/21/18.
 */
public class ViewSrcButton {

    private HTMLDivElement container = (HTMLDivElement) document.createElement("div");
    private HTMLAnchorElement info = (HTMLAnchorElement) document.createElement("a");

    private static final String REPO_ADDR = "https://github.com/treblereel/three4g-demo/tree/master/src/main/java/";


    public ViewSrcButton(){
        container.id = "viewSrcButton";

        info.href = "http://threejs.org";
        info.target= "_blank";
        info.rel = "noopener";
        info.textContent = "View source";

        container.appendChild(info);
        document.body.appendChild(container);

    }

    public void setLink(String clazz){
        info.href = REPO_ADDR+clazz.replaceAll("\\.","/")+".java";
    }


}
