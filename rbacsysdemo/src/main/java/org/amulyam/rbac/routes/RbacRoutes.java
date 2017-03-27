package org.amulyam.rbac.routes;

import io.netty.handler.codec.http.HttpMethod;
import org.restexpress.RestExpress;
import org.springframework.context.ApplicationContext;

/**
 * Created by amulyam on 27/03/17.
 */

/**
 * Routes class which defines URI of the APIs
 * the headers in {...} are to be used in URI itself
 * Additionally the API may contain parameters and Request headers
 */
public class RbacRoutes {
    public static void defineRoutes(RestExpress server, ApplicationContext context) {
        server.uri("/add/to/role/{uId}/{role}", context.getBean("userRoleController"))
                .noSerialization()
                .action("addUserToRole", HttpMethod.POST);

        server.uri("/remove/from/role/{uId}/{role}", context.getBean("userRoleController"))
                .noSerialization()
                .action("removeUserToRole", HttpMethod.DELETE);

        server.uri("/query", context.getBean("queryController"))
                .noSerialization()
                .action("query", HttpMethod.GET);

    }
}
