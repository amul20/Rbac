package org.amulyam.rbac.util;

/**
 * Created by amulyam on 27/03/17.
 */

/**
 * Utility class to keep all string and integer constants at a single place
 */
public class ServerConstants {
    public static final String USER_TABLE = "user";
    public static final String ROLE_TABLE = "roles";
    public static final String ACTION_TABLE = "actions";
    public static final String RESOURCE_TABLE = "resource";
    public static final String USER_ROLE_TABLE = "user_role";
    public static final String ROLE_RESOURCE_ACTION_TABLE = "rra";
    public static final String ROLE_HEADER = "role";
    public static final String RESOURCE_HEADER = "res";
    public static final String ACTION_HEADER = "action";
    public static final String ADMIN = "admin";
    public static final String ADMIN_ID = "adminId";
    public static final String ADMIN_PASS = "adminPassword";
    public static final String NEW_USER_ID = "uId";
    public static final String INCOMPLETE = "INCOMPLETE INFORMATION";
    public static final String AUTHROIZED = "AUTHORIZED";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
    public static final String USER_ADDED = "USER ADDED";
    public static final String USER_NOT_ADDED = "USER NOT ADDED";
    public static final String USER_REMOVED = "USER REMOVED";
    public static final String USER_NOT_REMOVED = "USER NOT REMOVED";
    public static final int HTTP_OK = 200;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int INETRNAL_SERVER_ERROR = 500;
    public static final int DUPLICATE = 406;
    public static final int ACCEPTED = 202;
    public static final int NOT_FOUND = 404;
    public static final String NO_ROLE = "NO ROLE FOUND";
    public static final String NO_USER = "NO USER FOUND";
}
