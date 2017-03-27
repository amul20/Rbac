package org.amulyam.rbac;

import org.amulyam.rbac.connector.MySqlConnector;
import org.amulyam.rbac.di.ServerDI;
import org.amulyam.rbac.routes.RbacRoutes;
import org.restexpress.RestExpress;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Created by amulyam on 27/03/17.
 */

/**
 * Main class to start our role based access control system
 * This class binds to 8080 port
 */
public class RbacServer {
    public static void main(String args[]) {
        final AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        try {
            appContext.register(ServerDI.class);
            appContext.refresh();
            MySqlConnector.getInstance();
            final RestExpress server = new RestExpress()
                    .setName("RBAC")
                    .setKeepAlive(true)
                    .setReuseAddress(true)
                    .setIoThreadCount(16)
                    .setExecutorThreadCount(100);
            RbacRoutes.defineRoutes(server, appContext);
            server.setMaxContentSize(1024 * 1024 * 200);
            server.bind(8080);
            server.awaitShutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
