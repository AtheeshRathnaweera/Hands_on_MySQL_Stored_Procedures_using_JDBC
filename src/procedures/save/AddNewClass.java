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

            String queryCreate = "CREATE PROCEDURE add_class (IN inpGrade VARCHAR(45), IN inpName VARCHAR(45), OUT returnMessage VARCHAR(255)) ";
            queryCreate += "BEGIN ";
            queryCreate += "IF NOT EXISTS(SELECT * FROM class WHERE grade=inpGrade && name=inpName) THEN ";
            queryCreate += "INSERT INTO class (grade, name) VALUES (inpGrade, inpName); ";
            queryCreate += "SET returnMessage='Class saved successfully.'; ";
            queryCreate += "ELSE ";
            queryCreate += "SET returnMessage='Not saved. Class already exists.'; ";
            queryCreate += "END IF; ";
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
