/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedures.save;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author atheesh27
 */
public class AddNewStudent {

    public static void procedure(Connection conn) {

        try (Statement statement = conn.createStatement()) {

            String queryDrop = "DROP PROCEDURE IF EXISTS add_student";

            String queryCreate = "CREATE PROCEDURE add_student (IN first_name VARCHAR(45), last_name VARCHAR(45), address VARCHAR(45), class_id INT, year VARCHAR(7) ) ";
            queryCreate += "BEGIN ";
            queryCreate += "DECLARE newStudentID INT; ";
            queryCreate += "INSERT INTO student (first_name, last_name, address) VALUES (first_name, last_name, address); ";
            queryCreate += "SET newStudentID = (SELECT id FROM student ORDER BY id DESC LIMIT 1); ";
            queryCreate += "INSERT INTO student_class_history (year, student_id, class_id) VALUES (year, newStudentID, class_id); ";
            queryCreate += "END";

            // drops the existing procedure if exists
            statement.execute(queryDrop);

            // then creates a new stored procedure
            statement.execute(queryCreate);

            statement.close();

            System.out.println("procedure.save : AddNewStudent : SC created successfully!");

        } catch (SQLException ex) {
            System.out.println("procedure.save : AddNewStudent : error occured : " + ex.toString());
        }

    }
}
