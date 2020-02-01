/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedures.auth;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author atheesh27
 */
public class SignIn {

    public static void procedure(Connection conn) {

        try (Statement statement = conn.createStatement()) {
            String queryDrop = "DROP PROCEDURE IF EXISTS signIn";

            String queryCreate = "CREATE PROCEDURE signIn (IN studentId INT, password VARCHAR(45),OUT returnMessage VARCHAR(255)) ";
            queryCreate += "BEGIN ";
            queryCreate += "DECLARE studentRecordCount INT; ";
            queryCreate += "SELECT COUNT(*) FROM student WHERE id = studentId INTO studentRecordCount; ";
            queryCreate += "IF (studentRecordCount > 0) THEN ";
            queryCreate += "INSERT INTO user_accounts (student_id,password,sign_in_date) VALUES (studentId,password,NOW()); ";
            queryCreate += "SET returnMessage='Student exists and signed successfully.'; ";
            queryCreate += "ELSE ";
            queryCreate += "SET returnMessage='Student not exists. Can not signIn.'; ";
            queryCreate += "END IF;";
            queryCreate += "END";

            // drops the existing procedure if exists
            statement.execute(queryDrop);

            // then creates a new stored procedure
            statement.execute(queryCreate);

            statement.close();

            System.out.println("procedures : SignIn : SC created successfully!");

        } catch (SQLException ex) {
            System.out.println("procedures : SignIn : SC create failed : " + ex.toString());
        }

    }

}
