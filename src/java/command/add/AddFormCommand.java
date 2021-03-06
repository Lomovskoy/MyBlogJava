package command.add;

import command.ActionCommand;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import resours.FileDirectoriesManager;

/**
 * Класс перехода на страницу добавления статьи
 * @author Lomovskoy
 */
public class AddFormCommand implements ActionCommand{
    
    /**
     * Метод перехода на страницу добавления статьи
     * @param request HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {  
        
        String path = request.getServletContext().getRealPath("")+File.separator
                +FileDirectoriesManager.getProperty("dir")+File.separator
                    +FileDirectoriesManager.getProperty("files");
        
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles,Collections.reverseOrder());
        request.setAttribute("images", listOfFiles);
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.addarticlepage");
        return page;
    }
}
