/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author atheesh27
 */
public class AuthController {
    
    public static void signIn(Connection conn,int id, String pw) {

        try (CallableStatement statementSignIn = conn.prepareCall("{call signIn(?,?,?)}")) {

            statementSignIn.setInt(1, id);
            statementSignIn.setString(2, pw);
            statementSignIn.registerOutParameter(3, Types.VARCHAR);

            statementSignIn.execute();

            System.out.println("controller.auth : AuthController : signIn : " + statementSignIn.getString("returnMessage"));

            statementSignIn.close();

        } catch (SQLException ex) {
            System.out.println("controller.auth : AuthController : signIn : " + ex.toString());
        }

    }

    public static void logIn(Connection conn,int id, String password) {
        try (CallableStatement statementSignIn = conn.prepareCall("{call logIn(?,?,?)}")) {

            statementSignIn.setInt(1, id);
            statementSignIn.setString(2, password);
            statementSignIn.registerOutParameter(3, Types.VARCHAR);

            statementSignIn.execute();

            System.out.println("controller.auth : AuthController : login : " + statementSignIn.getString("returnMessage"));

            statementSignIn.close();

        } catch (SQLException ex) {
            System.out.println("controller.auth : AuthController : login : " + ex.toString());
        }

    }

}
