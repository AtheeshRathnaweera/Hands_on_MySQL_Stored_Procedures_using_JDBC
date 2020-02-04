package procedures.auth;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author atheesh27
 */
public class LogIn {

    public static void procedure(Connection conn) {

        try (Statement statement = conn.createStatement()) {
            String queryDrop = "DROP PROCEDURE IF EXISTS logIn";

            String queryCreate = "CREATE PROCEDURE logIn (IN studentId INT,IN studentPassword VARCHAR(255),OUT returnMessage VARCHAR(255)) ";
            queryCreate += "BEGIN ";
            queryCreate += "DECLARE userPassword VARCHAR(255); ";
            queryCreate += "SELECT password FROM user_accounts WHERE student_id = studentId INTO userPassword; ";
            queryCreate += "IF (userPassword IS NULL) THEN ";
            queryCreate += "SET returnMessage='Invalid user id.'; ";
            queryCreate += "ELSE ";
            queryCreate += "IF (userPassword = studentPassword) THEN ";
            queryCreate += "SET returnMessage='Login successfull.'; ";
            queryCreate += "ELSE ";
            queryCreate += "SET returnMessage='Incorrect password.'; ";
            queryCreate += "END IF; ";
            queryCreate += "END IF;";
            queryCreate += "END";

            // drops the existing procedure if exists
            statement.execute(queryDrop);

            // then creates a new stored procedure
            statement.execute(queryCreate);

            statement.close();

            System.out.println("Procedures : auth : LogIn : SC created successfully!");

        } catch (SQLException ex) {
            System.out.println("Procedures : auth : LogIn : SC create failed : " + ex.toString());
        }

    }

}
