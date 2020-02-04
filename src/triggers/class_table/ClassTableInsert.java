package triggers.class_table;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author atheesh27
 */
public class ClassTableInsert {

    public static void create(Connection conn) {

        try (Statement statement = conn.createStatement()) {

            String queryDrop = "DROP TRIGGER IF EXISTS class_table_insert_trigger";

            String queryCreate = "CREATE TRIGGER class_table_insert_trigger ";
            queryCreate += "AFTER INSERT ON class ";
            queryCreate += "FOR EACH ROW ";
            queryCreate += "BEGIN ";
            queryCreate += "DECLARE beforeChangedValues VARCHAR(255); ";
            queryCreate += "INSERT INTO class_table_logs (class_id, grade, name, actionType, previousValues, userName, occuredTime) ";
            queryCreate += "VALUES ( NEW.id, NEW.grade, NEW.name, 'INSERT', beforeChangedValues, USER(), NOW()); ";
            queryCreate += "END; ";

            // drops the existing trigger if exists
            statement.execute(queryDrop);

            // then creates a new trigger
            statement.execute(queryCreate);

            statement.close();

            System.out.println("Triggers : class_table : Triggers : create : Trigger created successfully!");

        } catch (SQLException ex) {
            System.out.println("Triggers : class_table : Triggers : create : error : " + ex.toString());
        }

    }

}

//
//
//CREATE TRIGGER class_table_logs_trigger ON class
//AFTER INSERT
//AS
//DECLARE @insertedClassId int;
//DECLARE @insertedGrade VARCHAR(255);
//DECLARE @insertedbttt VARCHAR(255);
//DECLARE @actionType VARCHAR(255);
//SELECT @insertedClassId=i.id FROM INSERTED i;
//SELECT @insertedGrade=i.grade FROM INSERTED i;
//SELECT @insertedName=i.name FROM INSERTED i;
//SET @actionType='insert';
//INSERT INTO class_table_logs (class_id, grade, name, action, server_name, server_instance_name, time)
//VALUES ( @insertedClassId, @insertedGrade, @insertedName, @actionType, CAST(SERVERPROPERTY('MachineName') AS VARCHAR(255)), CAST(SERVERPROPERTY('ServerName') AS VARCHAR(255)), NOW());
