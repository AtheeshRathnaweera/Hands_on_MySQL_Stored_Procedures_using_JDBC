package procedures.retrieve;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author atheesh27
 */
public class GetClassData {

    public static void procedure(Connection conn) {

        try (Statement statement = conn.createStatement()) {

            String queryDrop = "DROP PROCEDURE IF EXISTS get_class_by_id";

            String queryCreate = "CREATE PROCEDURE get_class_by_id (IN classId INT) ";
            queryCreate += "BEGIN ";
            queryCreate += "SELECT * FROM class WHERE id = classId; ";
            queryCreate += "END";

            // drops the existing procedure if exists
            statement.execute(queryDrop);

            // then creates a new stored procedure
            statement.execute(queryCreate);

            statement.close();

            System.out.println("Procedures : retrieve : GetClassData : SC created successfully! ");

        } catch (SQLException ex) {
            System.out.println("Procedures : retrieve : GetClassData : error : " + ex.toString());
        }

    }

}
