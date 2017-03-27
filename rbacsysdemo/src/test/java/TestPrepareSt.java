import org.amulyam.rbac.connector.MySqlConnector;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amulyam on 27/03/17.
 */
public class TestPrepareSt {
    public static void main(String args[]) throws SQLException, NoSuchAlgorithmException {
        MySqlConnector connector = MySqlConnector.getInstance();
        Connection connection = connector.getConnection();
        String sql = "Select password from user where userId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"amulyam");
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        System.out.println(rs.getString(1));
    }
}
