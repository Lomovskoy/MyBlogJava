package command.admin;

import command.ActionCommand;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

/**
 * Класс отвечающий за переход на страницу администратора
 * @author Lomovskoy
 */
public class AdminCommand implements ActionCommand{
    
    /**
     * Метод отвечающий за переход на страницу администратора
     * @param request HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {  
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.adminlogin");
        return page;
    }
    
}
