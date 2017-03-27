package org.amulyam.rbac.connector;


import java.sql.*;

/**
 * Created by amulyam on 27/03/17.
 */

/**
 * Thread safe classs used to connect to mysql database
 * Provides a connection to the database
 * Also has functions to execute CRUD queries
 */
public class MySqlConnector {
    private static volatile MySqlConnector INSTANCE = null;
    private static final Object Lock = new Object();
    private static final String HOST_NAME = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE_NAME = "RBAC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private static final String DB_URL = "jdbc:mysql://" + HOST_NAME + ":" + PORT + "/" + DATABASE_NAME;

    /**
     * Private constructor to implement Singleton Pattern
     *
     * @throws ClassNotFoundException
     */
    private MySqlConnector() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    /**
     * Fucntion to get instance of this class
     *
     * @return Instance of MySqlConnector class
     */
    public static MySqlConnector getInstance() {
        if (INSTANCE == null) {
            synchronized (Lock) {
                if (INSTANCE == null) {
                    try {
                        INSTANCE = new MySqlConnector();
                    } catch (Exception e) {
                        throw new RuntimeException("issue in connector");
                    }
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Function to return Connection instance to the database
     * Connection to be closed by the User
     *
     * @return Connection instance
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
    }

    /**
     * Function to execute select statements
     *
     * @param statement
     * @return ResultSet of the Query
     * @throws SQLException
     */
    public ResultSet selectUsingPreparedStatement(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    /**
     * Function to execute insert, update and delete statements
     *
     * @param statement
     * @return number of rows updated by the operation
     * @throws SQLException
     */
    public int insertDeleteUpdateQuery(PreparedStatement statement) throws SQLException {
        return statement.executeUpdate();
    }


}
