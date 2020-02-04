package procedures.delete;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author atheesh27
 */
public class DeleteClass {
     public static void procedure(Connection conn) {

        try (Statement statement = conn.createStatement()) {

            String queryDrop = "DROP PROCEDURE IF EXISTS delete_class";

            String queryCreate = "CREATE PROCEDURE delete_class (IN classId INT) ";
            queryCreate += "BEGIN ";
            queryCreate += "DELETE FROM class WHERE id = classId; ";
            queryCreate += "END";

            // drops the existing procedure if exists
            statement.execute(queryDrop);

            // then creates a new stored procedure
            statement.execute(queryCreate);

            statement.close();

            System.out.println("Procedures : delete : DeleteClass : SC created successfully! ");

        } catch (SQLException ex) {
            System.out.println("Procedures : delete : DeleteClass : error : " + ex.toString());
        }
    }
}
