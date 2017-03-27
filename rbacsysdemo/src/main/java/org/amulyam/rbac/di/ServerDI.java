package org.amulyam.rbac.di;


import org.amulyam.rbac.controller.QueryController;
import org.amulyam.rbac.controller.UserRoleController;
import org.springframework.context.annotation.Bean;

import javax.inject.Singleton;

/**
 * Created by amulyam on 27/03/17.
 */

/**
 * Dependency Injection class for making beans for route class
 */
public class ServerDI {

    @Bean
    @Singleton
    public QueryController queryController() {
        return new QueryController();
    }

    @Bean
    @Singleton
    public UserRoleController userRoleController() {
        return new UserRoleController();
    }

}
