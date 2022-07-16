import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class IOBundles {
    public static void main(String[] args) {
        /** read the configuration INI file in resources/xxx
         * purpose: for multi configuration, like different language, locale etc.
         */

        // way1: default location with ResourceBundle
        // the config_zh.properties should be placed at resources/config_zh.properties
        // the file will be followed by  config+ "_zh", that is the default Locale
        ResourceBundle rb = ResourceBundle.getBundle("config");
        System.out.println("\nway1 keySet():" + rb.keySet().toString());
        System.out.println("Locale getdefault():" + Locale.getDefault());
        System.out.println("ResourceBundle getString(key1):" + rb.getString("key1"));
        System.out.println("ResourceBundle getString(key2):" + rb.getString("key2"));

        // way2:  absolute path without direct Bundle
        //  bundle  from FileReader
        try {
            //FileReader reader = new FileReader("D:\\java\\awt\\MyAwt\\src\\main\\resources\\config_zh.properties");
            BufferedReader reader = new BufferedReader(new FileReader("D:\\java\\awt\\MyAwt\\src\\main\\resources\\config_zh.properties"));
            ResourceBundle rs2 = new PropertyResourceBundle(reader);
            System.out.println("\n way2, bundle  from FileReader \nkeyset():" + rs2.keySet().toString());
            System.out.println("getString(key1):" + rs2.getString("key1"));
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }
}
