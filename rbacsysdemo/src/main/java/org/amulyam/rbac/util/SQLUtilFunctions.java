package org.amulyam.rbac.util;

import org.amulyam.rbac.connector.MySqlConnector;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amulyam on 27/03/17.
 */

/**
 * A Helper class which does all the logic
 * Contains functions to query mysql and get the data accordingly
 */
public class SQLUtilFunctions {

    /**
     * function to check whether role exists in the role table or not
     *
     * @param connection Connection instance of mySql
     * @param role       Role provided by the user
     * @return true if role exists in the database
     * @throws SQLException
     */
    public boolean checkIfRoleExists(Connection connection, String role) throws SQLException {
        String roleQuery = "select count(*) from " + ServerConstants.ROLE_TABLE + " where roleName=?";
        PreparedStatement statement = connection.prepareStatement(roleQuery);
        statement.setString(1, role.toLowerCase());
        ResultSet rs = MySqlConnector.getInstance().selectUsingPreparedStatement(statement);
        rs.next();
        int count = rs.getInt(1);
        return count == 1;
    }

    /**
     * function to check whether given user is admin
     *
     * @param connection Connection instance of mySql
     * @param userId     adminId provided by the user
     * @return true if user is admin
     * @throws SQLException
     */
    public boolean isAdmin(Connection connection, String userId) throws SQLException {
        String sql = "select count(*) from " + ServerConstants.USER_ROLE_TABLE + " where uId = ? and rId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userId);
        statement.setString(2, ServerConstants.ADMIN);
        ResultSet rs = MySqlConnector.getInstance().selectUsingPreparedStatement(statement);
        rs.next();
        int count = rs.getInt(1);
        return count == 1;
    }

    /**
     * function to check whether user has entered correct password or not
     *
     * @param connection Connection instance of mySql
     * @param userId     UserId provided by the user
     * @param password   Password provided by the user
     * @return true if user has entered his correct password
     * @throws SQLException
     */
    public boolean isAuthenticated(Connection connection, String userId, String password) throws NoSuchAlgorithmException, SQLException {
        String encPass = MD5Hash.getMd5Hash(password);
        String sql = "select count(*) from " + ServerConstants.USER_TABLE + " where userId = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userId);
        statement.setString(2, encPass);
        ResultSet rs = MySqlConnector.getInstance().selectUsingPreparedStatement(statement);
        rs.next();
        int count = rs.getInt(1);
        return count == 1;
    }

    /**
     * function to check whether user exists in the system ot not
     *
     * @param connection Connection instance of mySql
     * @param userId     UserId provided by the user
     * @return true if user exists in the database
     * @throws SQLException
     */
    public boolean checkIfUserExists(Connection connection, String userId) throws SQLException {
        String userQuery = "select count(*) from " + ServerConstants.USER_TABLE + " where userId = ?";
        PreparedStatement statement = connection.prepareStatement(userQuery);
        statement.setString(1, userId);
        ResultSet rs = MySqlConnector.getInstance().selectUsingPreparedStatement(statement);
        rs.next();
        int count = rs.getInt(1);
        return count == 1;
    }

    /**
     * function to insert entry into user_role table
     *
     * @param connection Connection instance of mySql
     * @param userId     User Id provided by the user
     * @param role       Role provided by the user
     * @return true if row is inserted in the database
     * @throws SQLException
     */
    public boolean assignUserToRole(Connection connection, String userId, String role) throws SQLException {
        String readQuery = "select count(*) from user_role where uId=? and rId=?";
        PreparedStatement st1 = connection.prepareStatement(readQuery);
        st1.setString(1,userId);
        st1.setString(2,role);
        ResultSet set = MySqlConnector.getInstance().selectUsingPreparedStatement(st1);
        set.next();
        int count = set.getInt(1);
        if(count==0){
            String query = "insert into user_role(uId,rId) values(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userId);
            statement.setString(2, role);
            int res = MySqlConnector.getInstance().insertDeleteUpdateQuery(statement);
            return res > 0;
        }else {
            return false;
        }
    }

    /**
     * function to remove entry from user_role table
     *
     * @param connection Connection instance of mySql
     * @param userId     User Id provided by the user
     * @param role       Role provided by the user
     * @return true if row is removed from the database
     * @throws SQLException
     */
    public boolean removeUserFromRole(Connection connection, String userId, String role) throws SQLException {
        String query = "delete from user_role where uId = ? and rId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, role);
        int res = MySqlConnector.getInstance().insertDeleteUpdateQuery(statement);
        return res > 0;
    }

    /**
     * Function to check if a user is authorized to do this action on this resource
     * It finds the roles of present user and checks in the role resource action table if there is a row with this resource and action
     *
     * @param connection Connection instance of mySql
     * @param userId     User Id given by the user
     * @param resource   Resource given by the user
     * @param action     Action to be performed
     * @return true if user is authorized to do this action on this resource;
     * @throws SQLException
     */
    public boolean isUserAuthorizedForAction(Connection connection, String userId, String resource, String action) throws SQLException {
        String query = "SELECT count(*) FROM " + ServerConstants.ROLE_RESOURCE_ACTION_TABLE + " a INNER JOIN " + ServerConstants.RESOURCE_TABLE +
                " b on a.resId=b.resId INNER JOIN " + ServerConstants.ACTION_TABLE + " c " + "ON a.aId=c.aId WHERE " +
                "b.resName=? and c.aName = ? and a.roleId IN (SELECT rId FROM " + ServerConstants.USER_ROLE_TABLE + " WHERE uId=?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, resource);
        statement.setString(2, action);
        statement.setString(3, userId);
        ResultSet rs = MySqlConnector.getInstance().selectUsingPreparedStatement(statement);
        rs.next();
        return rs.getInt(1) > 0;
    }
}
