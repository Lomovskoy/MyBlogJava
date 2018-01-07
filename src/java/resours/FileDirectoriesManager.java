
package resours;

/**
 *
 * @author pupil
 */
import java.util.ResourceBundle;

/**
 *
 * @author jvm
 */
public class FileDirectoriesManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.fileDirectories");
    private FileDirectoriesManager(){}
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}