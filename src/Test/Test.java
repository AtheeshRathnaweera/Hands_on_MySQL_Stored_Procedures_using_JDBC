package Test;

import java.sql.Connection;
import resources.CreateConnection;
import controllers.*;

/**
 *
 * @author atheesh27
 */
public class Test {

    public static void main(String args[]) {
        Connection conn = CreateConnection.getConnection();

        //AuthController.signIn(conn, 10, "sanga");
        //AuthController.logIn(conn, 10, "sanga");
        //RetrieveDataController.getStudentData(conn, 9, "2020");
        //RetrieveDataController.getClassById(conn, 1);
       // SaveDataController.addNewClass(conn, "6", "D");
        //SaveDataController.addNewStudent(conn, "Nuwan", "Kulasekara", "Maharagama", 1, "2020");
        //UpdateDataController.updateStudent(conn, "Chamalka", "Rathna", "Maharaga", 00);
       // UpdateDataController.updateClass(conn, "6", "D", 27);
       // DeleteController.deleteStudent(conn, 8);
        //DeleteController.deleteUserAccount(conn, 8);
       // DeleteController.deleteClass(conn, 27);
     

    }

}
