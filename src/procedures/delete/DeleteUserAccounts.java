package procedures.delete;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author atheesh27
 */
public class DeleteUserAccounts {

    public static void procedure(Connection conn) {

        try (Statement statement = conn.createStatement()) {

            String queryDrop = "DROP PROCEDURE IF EXISTS delete_user_account";

            String queryCreate = "CREATE PROCEDURE delete_user_account (IN studentId INT) ";
            queryCreate += "BEGIN ";
            queryCreate += "DELETE FROM user_accounts WHERE student_id = studentId; ";
            queryCreate += "END";

            // drops the existing procedure if exists
            statement.execute(queryDrop);

            // then creates a new stored procedure
            statement.execute(queryCreate);

            statement.close();

            System.out.println("Procedures : delete : DeleteUserAccounts : SC created successfully! ");

        } catch (SQLException ex) {
            System.out.println("Procedures : delete : DeleteUserAccounts : error : " + ex.toString());
        }

    }
}
