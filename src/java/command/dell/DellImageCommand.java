
package command.dell;

import command.ActionCommand;
import java.io.File;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import resours.FileDirectoriesManager;

/**
 *
 * @author imxo
 */
public class DellImageCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        
        String name = (String) request.getParameter("name");
        String filePath = request.getServletContext().getRealPath("")+File.separator
                +FileDirectoriesManager.getProperty("dir")+File.separator
                    +FileDirectoriesManager.getProperty("files");
        
        File file = new File(filePath+'/'+name);
        file.delete();
        
        String path = request.getServletContext().getRealPath("")+File.separator
                +FileDirectoriesManager.getProperty("dir")+File.separator
                    +FileDirectoriesManager.getProperty("files");
        
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        /*for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }*/
        
        request.setAttribute("images", listOfFiles);

        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.updatefileform");
        return page;
    }
    
}
