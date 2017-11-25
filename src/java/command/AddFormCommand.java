package command;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author pupil
 * Класс перехода на страницу добавления статьи
 */
public class AddFormCommand implements ActionCommand{
    
    @Override
    public String execute(HttpServletRequest request) {  
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.addarticlepage");
        return page;
    }
}
