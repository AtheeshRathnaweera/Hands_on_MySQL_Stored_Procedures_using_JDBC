package procedures.delete;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author atheesh27
 */
public class DeleteStudent {

    public static void procedure(Connection conn) {

        try (Statement statement = conn.createStatement()) {

            String queryDrop = "DROP PROCEDURE IF EXISTS delete_student";

            String queryCreate = "CREATE PROCEDURE delete_student (IN studentId INT) ";
            queryCreate += "BEGIN ";
            queryCreate += "DELETE FROM student WHERE id = studentId; ";
            queryCreate += "END";

            // drops the existing procedure if exists
            statement.execute(queryDrop);

            // then creates a new stored procedure
            statement.execute(queryCreate);

            statement.close();

            System.out.println("Procedures : delete : DeleteStudent : SC created successfully! ");

        } catch (SQLException ex) {
            System.out.println("Procedures : delete : DeleteStudent : error : " + ex.toString());
        }
    }

}

//If the CASCADES are set up delete query will work on the main table even though the foreign keys are exists.
//When a row delete from the main table, all other tables will be cleaned up automatically!


//ALTER TABLE student_class_history
//  ADD CONSTRAINT FK_History_Student FOREIGN KEY (student_id)     
//      REFERENCES student (id)
//      ON DELETE CASCADE    
//      ON UPDATE CASCADE
//  ;