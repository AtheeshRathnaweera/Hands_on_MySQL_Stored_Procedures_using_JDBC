package storedproclearn;

import java.sql.*;

public class CreateProcedures {

    private static Connection conn = null;

    public static void main(String[] args) {

        conn = CreateConnection.getConnection();

        if (conn != null) {
            addClassProc();
            addStudentProc();
            getStudentProc();
        } else {
            System.out.println("CreateProcedures : main : connection is null");
        }

    }

    //insert
    private static void addClassProc() {

        try (Statement statement = conn.createStatement()) {

            String queryDrop = "DROP PROCEDURE IF EXISTS add_class";

            String queryCreate = "CREATE PROCEDURE add_class (IN grade VARCHAR(45), name VARCHAR(45)) ";
            queryCreate += "BEGIN ";
            queryCreate += "INSERT INTO class (grade, name) VALUES (grade, name); ";
            queryCreate += "END";

            // drops the existing procedure if exists
            statement.execute(queryDrop);

            // then creates a new stored procedure
            statement.execute(queryCreate);

            statement.close();

            System.out.println("CreateProcedures : addClassProc : SC created successfully!");

        } catch (SQLException ex) {
            System.out.println("CreateProcedures : addClassProc : error occured : " + ex.toString());
        }

    }

    private static void addStudentProc() {

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

            System.out.println("CreateProcedures : addStudentProc : SC created successfully!");

        } catch (SQLException ex) {
            System.out.println("CreateProcedures : addClassProc : error occured : " + ex.toString());
        }

    }
    //insert

    //retrieve
    private static void getStudentProc() {

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

            System.out.println("CreateProcedures : getStudentProc : created successfully! ");

        } catch (SQLException ex) {
            System.out.println("CreateProcedures : getStudentProc : error occured : " + ex.toString());
        }

    }
    //retrieve

}
