
package command;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

public class ErrorCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
            
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.error");
        return page;
    }
}
