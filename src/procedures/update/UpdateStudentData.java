package procedures.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author atheesh27
 */
public class UpdateStudentData {

    public static void procedure(Connection conn) {

        try (Statement statement = conn.createStatement()) {

            String queryDrop = "DROP PROCEDURE IF EXISTS update_student";

            String queryCreate = "CREATE PROCEDURE update_student (IN firstName VARCHAR(45), IN lastName VARCHAR(45), IN address VARCHAR(45), IN studentId INT) ";
            queryCreate += "BEGIN ";
            queryCreate += "UPDATE student SET first_name = firstName, last_name = lastName, address = address WHERE id = studentId; ";
            queryCreate += "END";

            // drops the existing procedure if exists
            statement.execute(queryDrop);

            // then creates a new stored procedure
            statement.execute(queryCreate);

            statement.close();

            System.out.println("procedures : update : updateStudentData : SC created successfully! ");
        } catch (SQLException ex) {
            System.out.println("procedures : update : updateStudentData : error occured : " + ex.toString());
        }

    }

}
