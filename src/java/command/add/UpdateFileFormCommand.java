
package command;

import command.ActionCommand;
import java.io.File;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author pupil
 */
public class UpdateFileFormCommand implements ActionCommand{
    
    @Override
    public String execute(HttpServletRequest request) { 
        
        File folder = new File("D:\\JKTVR-16\\Lomovskoy\\my_blog_lomovskoy_image\\files");
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
