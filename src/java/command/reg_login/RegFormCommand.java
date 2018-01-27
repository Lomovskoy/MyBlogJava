package command.reg_login;

import command.ActionCommand;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author pupil
 */
public class RegFormCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.regform");
        return page;
    }
    
}
