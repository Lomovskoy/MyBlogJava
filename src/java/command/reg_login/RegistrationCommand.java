package command;

import classes.Cryptography;
import entity.Article;
import entity.Role;
import entity.User;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.jasper.tagplugins.jstl.ForEach;
import session.RoleFacade;
import session.UserFacade;

/**
 *
 * @author pupil
 */
public class RegistrationCommand implements ActionCommand {

    private UserFacade userFasade;
    private RoleFacade roleFasade;

    public RegistrationCommand() {
        Context context;
        try {
            context = new InitialContext();
            this.userFasade = (UserFacade) context.lookup("java:module/UserFacade");
            this.roleFasade = (RoleFacade) context.lookup("java:module/RoleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AdminCommand.class.getName()).log(Level.SEVERE, "Не удалось сессионный бин ", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.adminlogin");

        String login = (String) request.getParameter("login");
        String email = (String) request.getParameter("email");
        String password1 = (String) request.getParameter("password1");
        String password2 = (String) request.getParameter("password2");

        if (login != "" && password1 != "" && password2 != "" && email != "") {

            if (password1.equals(password2)) {

                Integer flag = 0;
                User repeatedUser1 = userFasade.findByLogin(login);
                if (repeatedUser1 != null) {
                    request.setAttribute("info", "Login уже занят");
                    page = resourceBundle.getString("page.regform");
                    flag += 1;
                }
                User repeatedUser2 = userFasade.findByEmail(email);
                if (repeatedUser2 != null) {
                    request.setAttribute("info", "Email уже занят");
                    page = resourceBundle.getString("page.regform");
                    flag += 1;
                }
                if (password1.length() <= 6 ) {
                    request.setAttribute("info", "Пароль слишком короткий мин 6 символов");
                    page = resourceBundle.getString("page.regform");
                    flag += 1;
                }

                if (flag == 0) {
                    String salts = Cryptography.getSalts();
                    String password = Cryptography.setEncriptPasssword(password1, salts);
                    Long idUser = 2L;
                    Role role = roleFasade.find(idUser);
                    Boolean active = true;
                    User user = new User(login, password, salts, active, role, email);

                    userFasade.create(user);

                    request.setAttribute("info", "Вы зарегистрированны");
                }

            } else {
                request.setAttribute("info", "Пароли не совпадают");
                page = resourceBundle.getString("page.regform");
            }
        } else {
            request.setAttribute("info", "Заполните все поля пожалуйста");
            page = resourceBundle.getString("page.regform");
        }
        return page;
    }

}
