package command.reg_login;

import command.ActionCommand;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

/**
 * Класс отвечающий за перенаправление на форму регистрации
 * @author Lomovskoy
 */
public class RegFormCommand implements ActionCommand{

    /**
     * Метод отвечающий за перенаправление на форму регистрации
     * @param request HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.regform");
        return page;
    }
    
}
