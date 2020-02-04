package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;

/**
 *
 * @author atheesh27
 */
public class SaveDataController {

    public static void addNewClass(Connection conn, String grade, String name) {

        try (CallableStatement statement = conn.prepareCall("{call add_class(?, ?, ?)}")) {

            statement.setString(1, grade);
            statement.setString(2, name);
            statement.registerOutParameter(3,Types.VARCHAR);

            statement.execute();

            System.out.println("controllers : SaveDataController : " + statement.getString("returnMessage"));
            
            statement.close();

        } catch (SQLIntegrityConstraintViolationException constIn) {
            System.out.println("controllers : SaveDataController : error occured : INVALID INPUTS " + constIn.toString());

        } catch (SQLException ex) {
            System.out.println("controllers : SaveDataController : error occured : " + ex.toString());
        }

    }

    public static void addNewStudent(Connection conn, String firstName, String lastName, String address, int classId, String year) {

        try (CallableStatement statement = conn.prepareCall("{call add_student(?, ?, ?, ?, ?)}")) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, address);
            statement.setInt(4, classId);
            statement.setString(5, year);

            statement.execute();
            statement.close();

            System.out.println("Controller : addNewStudent : student saved successfully !");

        } catch (SQLIntegrityConstraintViolationException consEx) {
            //a foreign constraint fails
            System.out.println("Controller : addNewStudent : error occured : INVALID INPUTS . " + consEx.toString());
        } catch (SQLException ex) {
            System.out.println("Controller : addNewStudent : error occured : " + ex.toString());
        }
    }

}
