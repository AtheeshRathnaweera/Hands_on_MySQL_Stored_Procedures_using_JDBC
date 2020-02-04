package Test;

import java.sql.Connection;
import resources.CreateConnection;
import procedures.auth.*;
import procedures.retrieve.*;
import procedures.save.*;
import procedures.update.*;
import procedures.delete.*;

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
        GetClassData.procedure(myConnection);
        //retrieve
        
        //save
        AddNewClass.procedure(myConnection);
        AddNewStudent.procedure(myConnection);
        //save
        
        //update
        UpdateStudentData.procedure(myConnection);
        UpdateClassData.procedure(myConnection);
        //update
        
        DeleteStudent.procedure(myConnection);
        DeleteUserAccounts.procedure(myConnection);
        DeleteClass.procedure(myConnection);
        
        
    
    }
    
    
    
}
