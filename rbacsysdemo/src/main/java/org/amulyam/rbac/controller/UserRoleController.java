package org.amulyam.rbac.controller;

import org.amulyam.rbac.connector.MySqlConnector;
import org.amulyam.rbac.util.ResponseUtils;
import org.amulyam.rbac.util.SQLUtilFunctions;
import org.amulyam.rbac.util.ServerConstants;
import org.amulyam.rbac.util.Utils;
import org.restexpress.Request;
import org.restexpress.Response;

import java.sql.Connection;

/**
 * Created by amulyam on 27/03/17.
 */

/**
 * Controller class for adding and removing a user from a role
 * Takes userId and password from the request
 * Only admin can use this API so first authorization takes place
 * The user and role has to exist before hand
 */
public class UserRoleController {
    /**
     * Uses HTTP POST Method
     * Controller for adding a user to a particular role
     *
     * @param request  Request API from client
     * @param response Response Object in which response parameters will be set
     */
    public void addUserToRole(Request request, Response response) {
        ResponseUtils responseUtils = new ResponseUtils();
        // connection will be automatically closed after exiting try block
        try (Connection connection = MySqlConnector.getInstance().getConnection()) {
            SQLUtilFunctions utils = new SQLUtilFunctions();
            String userId = request.getHeader(ServerConstants.ADMIN_ID);
            String password = request.getHeader(ServerConstants.ADMIN_PASS);
            if (Utils.isNotEmpty(userId) && Utils.isNotEmpty(password)) {
                if (utils.isAuthenticated(connection, userId, password) && utils.isAdmin(connection, userId)) {
                    String role = request.getHeader(ServerConstants.ROLE_HEADER);
                    if (Utils.isNotEmpty(role) && utils.checkIfRoleExists(connection,role)) {
                        String newUserId = request.getHeader(ServerConstants.NEW_USER_ID);
                        if (utils.checkIfUserExists(connection, newUserId)) {
                            boolean ans = utils.assignUserToRole(connection, newUserId, role);
                            if (ans) {
                                responseUtils.setSuccessResponse(response, ServerConstants.USER_ADDED, ServerConstants.HTTP_OK);
                            } else {
                                responseUtils.setUnSuccessfulResponse(response, ServerConstants.USER_NOT_ADDED, ServerConstants.DUPLICATE);
                            }
                        } else {
                            responseUtils.setUnSuccessfulResponse(response, ServerConstants.NO_USER, ServerConstants.NOT_FOUND);
                        }
                    } else {
                        //role doesn't exist.. can;t add user
                        responseUtils.setUnSuccessfulResponse(response, ServerConstants.NO_ROLE, ServerConstants.NOT_FOUND);
                    }
                } else {
                    //Not authorized to assign a role
                    responseUtils.setUnAuthorizedResponse(response, ServerConstants.UNAUTHORIZED);
                }
            } else {
                responseUtils.setUnAuthorizedResponse(response, ServerConstants.INCOMPLETE);
            }
        } catch (Exception e) {
            responseUtils.setInternalServerErrorResponse(response);
            e.printStackTrace();
        }
    }

    /**
     * Uses HTTP DELETE Method
     * Controller for adding a user to a particular role
     *
     * @param request  Request API from client
     * @param response Response Object in which response parameters will be set
     */
    public void removeUserToRole(Request request, Response response) {
        ResponseUtils responseUtils = new ResponseUtils();
        // connection will be automatically closed after exiting try block
        try (Connection connection = MySqlConnector.getInstance().getConnection()) {
            SQLUtilFunctions utils = new SQLUtilFunctions();
            String userId = request.getHeader(ServerConstants.ADMIN_ID);
            String password = request.getHeader(ServerConstants.ADMIN_PASS);
            if (Utils.isNotEmpty(userId) && Utils.isNotEmpty(password)) {
                if (utils.isAuthenticated(connection, userId, password) && utils.isAdmin(connection, userId)) {
                    String role = request.getHeader(ServerConstants.ROLE_HEADER);
                    if (Utils.isNotEmpty(role) && utils.checkIfRoleExists(connection,role)) {
                        String newUserId = request.getHeader(ServerConstants.NEW_USER_ID);
                        if (utils.checkIfUserExists(connection, newUserId)) {
                            boolean ans = utils.removeUserFromRole(connection, newUserId, role);
                            if (ans) {
                                responseUtils.setSuccessResponse(response, ServerConstants.USER_REMOVED, ServerConstants.HTTP_OK);
                            } else {
                                responseUtils.setUnSuccessfulResponse(response, ServerConstants.USER_NOT_REMOVED, ServerConstants.DUPLICATE);
                            }
                        } else {
                            //user doesn't exist
                            responseUtils.setUnSuccessfulResponse(response, ServerConstants.NO_USER, ServerConstants.NOT_FOUND);
                        }
                    } else {
                        //role doesn't exist.. can;t add user
                        responseUtils.setUnSuccessfulResponse(response, ServerConstants.NO_ROLE, ServerConstants.NOT_FOUND);
                    }
                } else {
                    //Not authorized to assign a role
                    responseUtils.setUnAuthorizedResponse(response, ServerConstants.UNAUTHORIZED);
                }
            } else {
                responseUtils.setUnAuthorizedResponse(response, ServerConstants.INCOMPLETE);
            }
        } catch (Exception e) {
            responseUtils.setInternalServerErrorResponse(response);
            e.printStackTrace();
        }
    }
}
