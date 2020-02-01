/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procedures.retrieve;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author atheesh27
 */
public class GetStudentData {

    public static void procedure(Connection conn) {

        try (Statement statement = conn.createStatement()) {

            String queryDrop = "DROP PROCEDURE IF EXISTS get_student";

            String queryCreate = "CREATE PROCEDURE get_student (IN studentId INT,IN year VARCHAR(10)) ";
            queryCreate += "BEGIN ";
            queryCreate += "SELECT student.id,student.first_name,student.last_name,student.address,student_class_history.year,student_class_history.class_id FROM student INNER JOIN student_class_history ON student_class_history.student_id = student.id WHERE student.id = studentId AND student_class_history.year = year; ";
            queryCreate += "END";

            // drops the existing procedure if exists
            statement.execute(queryDrop);

            // then creates a new stored procedure
            statement.execute(queryCreate);

            statement.close();

            System.out.println("procedures.retrieve : GetStudentData : created successfully! ");

        } catch (SQLException ex) {
            System.out.println("procedures.retrieve : GetStudentData : error occured : " + ex.toString());
        }

    }

}
