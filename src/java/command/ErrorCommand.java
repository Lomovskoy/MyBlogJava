package command;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

/**
 * Класс перенаправления настраницу ошибки
 * @author Lomovskoy
 */
public class ErrorCommand implements ActionCommand{
    
    /**
     * Метод направляющий на страницу ошибки
     * @param request
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
            
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.error");
        return page;
    }
}
