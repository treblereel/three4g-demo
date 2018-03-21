package org.jboss.errai.demo.client.local;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLDivElement;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import static elemental2.dom.DomGlobal.window;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/21/18.
 */

public class LinkDiv {

    private HTMLDivElement container = (HTMLDivElement) DomGlobal.document.createElement("div");
    private HTMLAnchorElement info = (HTMLAnchorElement) DomGlobal.document.createElement("a");

    private static final String REPO_ADDR = "https://github.com/treblereel/three4g-demo/tree/master/src/main/java/org/jboss/errai/demo/client/local/examples";

    @PostConstruct
    public void init(){

        window.alert("OLOLO");
        container.id = "viewSrcButton";

        info.href = "http://threejs.org";
        info.target= "_blank";
        info.rel = "noopener";
        info.textContent = "View source";

        container.appendChild(info);
        //document.body.appendChild(container);
    }

    public LinkDiv setLink(Attachable candidate){
        String pkg = candidate.getClass().getPackage().getName().replaceAll(".","/");
        info.href = REPO_ADDR+"/"+pkg+"/"+candidate.getClass().getSimpleName()+".java";
        return this;
    }

    public LinkDiv hide(){
        container.style.display = "none";
        return this;
    }
}
