package resours;

import java.util.ResourceBundle;

/**
 * Класс менеджер, возвращающий пути, директории по ключу
 * @author Lomovskoy
 */
public class FileDirectoriesManager {
    
    private final static ResourceBundle resourceBundle = 
            ResourceBundle.getBundle("properties.fileDirectories");
    
    private FileDirectoriesManager(){}
    /**
     * Сетод возврата директории по ключу
     * @param key
     * @return String
     */
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}