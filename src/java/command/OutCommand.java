package command;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pupil
 * Класс выхода из сессии
 */
public class OutCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        
        session.setAttribute("admin", null);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.index");
        return page;
    }
    
    
    
}
