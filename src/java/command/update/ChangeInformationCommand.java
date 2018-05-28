package command.update;

import classes.Cryptography;
import command.ActionCommand;
import command.admin.AdminCommand;
import entity.User;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import session.RoleFacade;
import session.UserFacade;

/**
 * Класс отвечающий за изменение информации о пользователя
 * @author Lomovskoy
 */
public class ChangeInformationCommand implements ActionCommand {

    private UserFacade userFasade;
    private RoleFacade roleFasade;

    /**
     * Конструктор реализующий поиск в контексте сервера придложений и 
     * подключение нужного бина в поле класса.
     */
    public ChangeInformationCommand() {
        Context context;
        try {
            context = new InitialContext();
            this.userFasade = (UserFacade) context.lookup("java:module/UserFacade");
            this.roleFasade = (RoleFacade) context.lookup("java:module/RoleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AdminCommand.class.getName()).log(Level.SEVERE, "Не удалось сессионный бин ", ex);
        }
    }

    /**
     * Метот отвечающий за изменение информации о пользователя
     * @param request HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.information");

        String login = (String) request.getParameter("login");
        String email = (String) request.getParameter("email");
        String password1 = (String) request.getParameter("password1");
        String password2 = (String) request.getParameter("password2");

        User user = (User) session.getAttribute("user");

        if (!"".equals(login)) {
            User repeatedUser1 = userFasade.findByLogin(login);
            if (repeatedUser1 != null) {
                request.setAttribute("info", "Login уже занят");
                page = resourceBundle.getString("page.information");
            } else {
                user.setLogin(login);
            }
        }

        if (!"".equals(email)) {
            User repeatedUser2 = userFasade.findByEmail(email);
            if (repeatedUser2 != null) {
                request.setAttribute("info", "Email уже занят");
                page = resourceBundle.getString("page.information");
            } else {
                user.setEmail(email);
            }
        }
        if (!"".equals(password1) && !"".equals(password2)) {
            if (password1.length() <= 6) {
                request.setAttribute("info", "Пароль слишком короткий мин 6 символов");
                page = resourceBundle.getString("page.information");
            } else if (password1.equals(password2)) {
                String salts = Cryptography.getSalts();
                String password = Cryptography.setEncriptPasssword(password1, salts);
                user.setPassword(password);
                user.setSalts(salts);
            }else{
                request.setAttribute("info", "Пароли не совпадают");
                page = resourceBundle.getString("page.information");
            }
        }
        session.setAttribute("user", user);
        userFasade.edit(user);

        return page;
    }

}
