package procedures.save;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author atheesh27
 */
public class AddNewClass {

    public static void procedure(Connection conn) {

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

            System.out.println("Procdures : save : AddNewClass : SC created successfully!");

        } catch (SQLException ex) {
            System.out.println("Procdures : save : AddNewClass :  error occured : " + ex.toString());
        }

    }

}
