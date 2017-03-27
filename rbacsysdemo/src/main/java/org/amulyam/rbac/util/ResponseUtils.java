package org.amulyam.rbac.util;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.restexpress.Response;

/**
 * Created by amulyam on 27/03/17.
 */

/**
 * Class to set response headers
 */
public class ResponseUtils {

    /**
     * Function to set success response code in response
     *
     * @param response Response object of API
     * @param status   Status message to be displayed
     * @param code     Status Code to be set in response
     */
    public void setSuccessResponse(Response response, String status, int code) {
        response.setResponseCode(code);
        response.setResponseStatus(new HttpResponseStatus(ServerConstants.HTTP_OK, status));
        setCommonHeaders(response);
    }

    /**
     * Function to set unsuccessful response
     *
     * @param response Response object of API
     * @param status   Status message to be displayed
     * @param code     Status Code to be set in response
     */
    public void setUnSuccessfulResponse(Response response, String status, int code) {
        response.setResponseCode(code);
        response.setResponseStatus(new HttpResponseStatus(ServerConstants.HTTP_UNAUTHORIZED, status));
        setCommonHeaders(response);
    }

    /**
     * Function to set unauthorized response
     *
     * @param response Response object of API
     * @param status   Status message to be displayed
     */
    public void setUnAuthorizedResponse(Response response, String status) {
        response.setResponseCode(ServerConstants.HTTP_UNAUTHORIZED);
        response.setResponseStatus(new HttpResponseStatus(ServerConstants.HTTP_UNAUTHORIZED, status));
        setCommonHeaders(response);
    }

    /**
     * Function to set response code in case of any exceptions
     *
     * @param response Response object of API
     */
    public void setInternalServerErrorResponse(Response response) {
        response.setResponseCode(ServerConstants.INETRNAL_SERVER_ERROR);
        response.setResponseStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
        setCommonHeaders(response);
    }

    /**
     * Function to set common headers in response
     *
     * @param response Response object of API
     */
    private void setCommonHeaders(Response response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader(HttpHeaders.Names.CACHE_CONTROL, "no-store, no-cache, must-revalidate");
        response.addHeader(HttpHeaders.Names.EXPIRES, "Thu, 01 Jan 1970 00:00:00 GMT");
    }
}
