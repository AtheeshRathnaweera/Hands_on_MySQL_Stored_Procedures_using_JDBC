package Test;

import java.sql.Connection;
import resources.CreateConnection;
import procedures.auth.*;
import procedures.retrieve.*;
import procedures.save.*;

/**
 *
 * @author atheesh27
 */
public class CreateProcedures {
    
    public static void main(String args[]){
        Connection myConnection = CreateConnection.getConnection();
        
        //auth
        LogIn.procedure(myConnection);
        SignIn.procedure(myConnection);
        //auth
        
        //retrieve
        GetStudentData.procedure(myConnection);
        //retrieve
        
        //save
        AddNewClass.procedure(myConnection);
        AddNewStudent.procedure(myConnection);
        //save
        
        
    
    }
    
    
    
}
