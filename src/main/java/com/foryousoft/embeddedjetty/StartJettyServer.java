package com.foryousoft.embeddedjetty;

import org.apache.log4j.Logger;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: i-tang
 * Date: 15 juin 2012
 * Time: 18:56:31
 */
public class StartJettyServer {

    public static Logger logger = Logger.getLogger(StartJettyServer.class.toString());

    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);
        logger.info("To connect : http://localhost:8080/MyApp/");
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/MyApp");
        webAppContext.setResourceBase("web/WEB-INF/");
        webAppContext.setDescriptor("web/WEB-INF/web.xml");
        webAppContext.setParentLoaderPriority(true);

        Configuration.ClassList classlist = Configuration.ClassList.setServerDefault( server );
        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration" );

        webAppContext.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$" );

        webAppContext.setServer(server);
        server.setHandler(webAppContext);
        server.start();
        server.join();
    }
}

