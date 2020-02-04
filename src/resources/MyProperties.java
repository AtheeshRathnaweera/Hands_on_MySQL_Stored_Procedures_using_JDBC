package resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author atheesh27
 */
public class MyProperties {

    public String dbName;
    public String userName;
    public String password;

    public MyProperties() {
        
        try {
            Properties prop = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("resources/config.properties");
            prop.load(stream);
            
            dbName = prop.getProperty("dbName");
            userName = prop.getProperty("userName");
            password = prop.getProperty("password");
            
            
        } catch (IOException ex) {
            Logger.getLogger(MyProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
