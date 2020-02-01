/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

        String dbURL = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(dbURL, user, password);
        } catch (SQLException ex) {
            System.out.println("CreateConnection class : error in 30 : "+ex);
        }

    }

    public static java.sql.Connection getConnection() {
        if(connection == null){
            dbConnection = new CreateConnection();
            System.out.println("new connection created.");
        }else{
            System.out.println("connection is not null");
        }
        return connection;

    }
    
}
