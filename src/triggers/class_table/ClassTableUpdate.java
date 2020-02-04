package triggers.class_table;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author atheesh27
 */
public class ClassTableUpdate {
    
    
    public static void create(Connection conn) {

        try (Statement statement = conn.createStatement()) {

            String queryDrop = "DROP TRIGGER IF EXISTS class_table_update_trigger";

            String queryCreate = "CREATE TRIGGER class_table_update_trigger ";
            queryCreate += "AFTER UPDATE ON class ";
            queryCreate += "FOR EACH ROW ";
            queryCreate += "BEGIN ";
            queryCreate += "DECLARE beforeChangedValues VARCHAR(255); ";
            queryCreate += "SET beforeChangedValues=CONCAT(OLD.grade, ',', OLD.name); ";
            queryCreate += "INSERT INTO class_table_logs (class_id, grade, name, actionType, previousValues, userName, occuredTime) ";
            queryCreate += "VALUES ( NEW.id, NEW.grade, NEW.name, 'UPDATE', beforeChangedValues, USER(), NOW()); ";
            queryCreate += "END; ";

            // drops the existing trigger if exists
            statement.execute(queryDrop);

            // then creates a new trigger
            statement.execute(queryCreate);

            statement.close();

            System.out.println("Triggers : class_table : Update : Trigger created successfully!");

        } catch (SQLException ex) {
            System.out.println("Triggers : class_table : Update : error : " + ex.toString());
        }

    }
    
}
