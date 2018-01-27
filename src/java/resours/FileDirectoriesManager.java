package resours;

import java.util.ResourceBundle;

/**
 *
 * @author imxo
 */
public class FileDirectoriesManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.fileDirectories");
    private FileDirectoriesManager(){}
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}