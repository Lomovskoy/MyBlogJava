/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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