package command.reg_login;

import command.ActionCommand;
import command.EmptyCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pupil Класс выхода из сессии
 */
public class OutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        session.setAttribute("user", null);

        return new EmptyCommand().execute(request);
        /*
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.index");
        return page;
         */
    }

}
