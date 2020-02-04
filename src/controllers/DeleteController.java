package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author atheesh27
 */
public class DeleteController {

    public static void deleteStudent(Connection conn, int studentId) {

        try (CallableStatement statement = conn.prepareCall("{call delete_student(?)}")) {
            statement.setInt(1, studentId);

            int affectedCount = statement.executeUpdate();
            statement.close();

            if (affectedCount > 0) {
                System.out.println("DeleteController : deleteStudent : student deleted successfully ! " + Integer.toString(affectedCount));
            } else {
                System.out.println("DeleteController : deleteStudent : nothing deleted ! ");
            }

        } catch (SQLException ex) {
            System.out.println("DeleteController : deleteStudent : error occured : " + ex.toString());
        }
    }

    public static void deleteUserAccount(Connection conn, int studentId) {
        
        try (CallableStatement statement = conn.prepareCall("{call delete_user_account(?)}")) {
            statement.setInt(1, studentId);

            int affectedCount = statement.executeUpdate();
            statement.close();

            if (affectedCount > 0) {
                System.out.println("DeleteController : deleteUserAccount : user deleted successfully ! " + Integer.toString(affectedCount));
            } else {
                System.out.println("DeleteController : deleteUserAccount : nothing deleted ! ");
            }

        } catch (SQLException ex) {
            System.out.println("DeleteController : deleteUserAccount : error occured : " + ex.toString());
        }

    }
    
      public static void deleteClass(Connection conn, int classId) {
        
        try (CallableStatement statement = conn.prepareCall("{call delete_class(?)}")) {
            statement.setInt(1, classId);

            int affectedCount = statement.executeUpdate();
            statement.close();

            if (affectedCount > 0) {
                System.out.println("DeleteController : deleteClass : class deleted successfully ! " + Integer.toString(affectedCount));
            } else {
                System.out.println("DeleteController : deleteClass : nothing deleted ! ");
            }

        } catch (SQLException ex) {
            System.out.println("DeleteController : deleteClass : error occured : " + ex.toString());
        }

    }
}
