package procedures.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author atheesh27
 */
public class UpdateClassData {
    
     public static void procedure(Connection conn) {

        try (Statement statement = conn.createStatement()) {

            String queryDrop = "DROP PROCEDURE IF EXISTS update_class";

            String queryCreate = "CREATE PROCEDURE update_class (IN inpGrade VARCHAR(45), IN inpName VARCHAR(45), IN classId INT) ";
            queryCreate += "BEGIN ";
            queryCreate += "UPDATE class SET grade = inpGrade, name = inpName WHERE id = classId; ";
            queryCreate += "END";

            // drops the existing procedure if exists
            statement.execute(queryDrop);

            // then creates a new stored procedure
            statement.execute(queryCreate);

            statement.close();

            System.out.println("procedures: update : updateClassData : SC created successfully! ");
        } catch (SQLException ex) {
            System.out.println("procedures : update : updateClassData : error occured : " + ex.toString());
        }

    }
    
}
