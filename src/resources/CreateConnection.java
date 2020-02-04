package resources;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author atheesh27
 */
public class CreateConnection {

    private static CreateConnection dbConnection;
    private static java.sql.Connection connection;

    private CreateConnection() {
        //?allowMultiQueries=true
        
        MyProperties myProperties = new MyProperties();
       
        String dbURL = myProperties.dbName;
        String user = myProperties.userName;
        String password = myProperties.password;

        try {
            connection = DriverManager.getConnection(dbURL, user, password);
        } catch (SQLException ex) {
            System.out.println("CreateConnection class : error in 30 : " + ex);
        }

    }

    public static java.sql.Connection getConnection() {
        if (connection == null) {
            dbConnection = new CreateConnection();
            System.out.println("new connection created.");
        } else {
            System.out.println("connection is not null");
        }
        return connection;

    }

}
