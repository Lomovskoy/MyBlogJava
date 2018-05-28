package command.reg_login;

import command.ActionCommand;
import command.EmptyCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Класс выхода из сессии
 * @author Lomovskoy 
 */
public class OutCommand implements ActionCommand {

    /**
     * Метод выхода из сессии
     * @param request HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        session.setAttribute("user", null);
        return new EmptyCommand().execute(request);
    }

}
