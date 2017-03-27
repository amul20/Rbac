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
 * Controller class for the Query API asked in the project
 * Takes userId, resource name and action name as input in API
 * Returns whether particular user has authorization of this action on this particular resource
 */
public class QueryController {

    /**
     * Uses HTTP GET Method
     * Function for Query API
     *
     * @param request  Request API from client
     * @param response Response Object in which response parameters will be set
     */
    public void query(Request request, Response response) {
        ResponseUtils responseUtils = new ResponseUtils();
        // connection will be automatically closed after exiting try block
        try (Connection connection = MySqlConnector.getInstance().getConnection()) {
            SQLUtilFunctions sqlUtils = new SQLUtilFunctions();
            String userId = request.getHeader(ServerConstants.NEW_USER_ID);
            String resource = request.getHeader(ServerConstants.RESOURCE_HEADER);
            String action = request.getHeader(ServerConstants.ACTION_HEADER);
            if (Utils.isNotEmpty(userId) && Utils.isNotEmpty(resource) && Utils.isNotEmpty(action)) {
                boolean ans = sqlUtils.isUserAuthorizedForAction(connection, userId, resource, action);
                if (ans) {
                    responseUtils.setSuccessResponse(response, ServerConstants.AUTHROIZED, ServerConstants.ACCEPTED);
                } else {
                    responseUtils.setUnAuthorizedResponse(response, ServerConstants.UNAUTHORIZED);
                }
            } else {
                // incomplete parameters from the request
                responseUtils.setUnAuthorizedResponse(response, ServerConstants.INCOMPLETE);
            }
        } catch (Exception e) {
            responseUtils.setInternalServerErrorResponse(response);
            e.printStackTrace();
        }
    }


}
