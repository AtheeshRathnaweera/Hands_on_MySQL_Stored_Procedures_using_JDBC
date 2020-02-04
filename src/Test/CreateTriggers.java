package Test;

import java.sql.Connection;
import resources.CreateConnection;
import triggers.class_table.*;

/**
 *
 * @author atheesh27
 */
public class CreateTriggers {
    
    public static void main(String args[]){
        
        Connection myConnection = CreateConnection.getConnection();
        
        ClassTableInsert.create(myConnection);
        ClassTableUpdate.create(myConnection);
        ClassTableDelete.create(myConnection);
        
    
    }
    
}
