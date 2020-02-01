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

        //AuthController.signIn(conn, 1, "sanga");
        //AuthController.logIn(conn, 1, "sanga");
        //RetrieveDataController.getStudentData(conn, 8,"2020");
        //SaveDataController.addNewClass(conn, "2", "Olu");
        SaveDataController.addNewStudent(conn, "Danushi", "Karunarathne", "Malabe", 6, "2020");
        
        
    }

}
