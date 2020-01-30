package storedproclearn;

import java.sql.*;

public class StoredProcLearn {

    private static Connection conn = null;

    public static void main(String[] args) {

        conn = CreateConnection.getConnection();

        if (conn != null) {
            addClassProc();
            addStudentProc();
            getStudentProc();
        } else {
            System.out.println("StoredProcLearn : main : connection is null");
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

            System.out.println("StoredProcLearn : addClassProc : SC created successfully!");

        } catch (SQLException ex) {
            System.out.println("StoredProcLearn : addClassProc : error occured : " + ex.toString());
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

            System.out.println("StoredProcLearn : addStudentProc : SC created successfully!");

        } catch (SQLException ex) {
            System.out.println("StoredProcLearn : addClassProc : error occured : " + ex.toString());
        }

    }
    //insert

    //retrieve
    private static void getStudentProc() {

        try (Statement statement = conn.createStatement()) {

            String queryDrop = "DROP PROCEDURE IF EXISTS get_student";

            String queryCreate = "CREATE PROCEDURE get_student (IN studentId INT) ";
            queryCreate += "BEGIN ";
            queryCreate += "SELECT * FROM student WHERE id = studentId; ";
            queryCreate += "END";

            // drops the existing procedure if exists
            statement.execute(queryDrop);

            // then creates a new stored procedure
            statement.execute(queryCreate);

            statement.close();

            System.out.println("StoredProcLearn : getStudentProc : created successfully! ");

        } catch (SQLException ex) {
            System.out.println("StoredProcLearn : getStudentProc : error occured : " + ex.toString());
        }

    }
    //retrieve

}
