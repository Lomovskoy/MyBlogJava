package command;

import java.io.File;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import resours.FileDirectoriesManager;

/**
 *
 * @author pupil
 * Класс перехода на страницу добавления статьи
 */
public class AddFormCommand implements ActionCommand{
    
    @Override
    public String execute(HttpServletRequest request) {  
        
        String path = request.getServletContext().getRealPath("")+File.separator
                +FileDirectoriesManager.getProperty("dir")+File.separator
                    +FileDirectoriesManager.getProperty("files");
        
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        
        request.setAttribute("images", listOfFiles);
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.addarticlepage");
        return page;
    }
}
