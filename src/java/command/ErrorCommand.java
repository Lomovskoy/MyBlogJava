
package command;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @author pupil
 * Класс перенаправления настраницу ошибки
 */
public class ErrorCommand implements ActionCommand{
    /**
     * 
     * @param request
     * @return 
     * Переназначенный метод родителя, направляющий на страницу ошибки
     */
    @Override
    public String execute(HttpServletRequest request) {
            
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.error");
        return page;
    }
}
