package org.jboss.errai.demo.client.local;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLDivElement;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static elemental2.dom.DomGlobal.document;

/**
 * @author Dmitrii Tikhomirov <chani@me.com>
 * Created by treblereel on 3/21/18.
 */
@Dependent
public class ViewSrcButton {

    private HTMLDivElement container = (HTMLDivElement) document.createElement("div");
    private HTMLAnchorElement info = (HTMLAnchorElement) document.createElement("a");

    private static final String REPO_ADDR = "https://github.com/treblereel/three4g-demo/tree/master/src/main/java/";

    @Inject
    Logger logger;

    @PostConstruct
    public void ViewSrcButton(){
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
