package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Configurations {
public static Map<String,String> file= new HashMap<>();


public static Properties readProperties(String BASE_URI) {

        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\configuration\\config.properties");
            Properties prop = new Properties();
            prop.load(fis);

          
            return prop;
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
}

}



