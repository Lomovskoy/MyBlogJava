package command.reg_login;

import classes.Cryptography;
import command.ActionCommand;
import command.admin.AdminCommand;
import entity.Role;
import entity.User;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import session.RoleFacade;
import session.UserFacade;

/**
 * Класс отвечающий за регистрацию пользователя
 * @author Lomovskoy
 */
public class RegistrationCommand implements ActionCommand {

    private UserFacade userFasade;
    private RoleFacade roleFasade;

    /**
     * Конструктор реализующий поиск в контексте сервера придложений и 
     * подключение нужного бина в поле класса.
     */
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

    /**
     * Метод отвечающий за регистрациюб пользователя
     * @param request HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.adminlogin");

        String login = (String) request.getParameter("login").replaceAll("\\s+","");
        String email = (String) request.getParameter("email").replaceAll("\\s+","");
        String password1 = (String) request.getParameter("password1");
        String password2 = (String) request.getParameter("password2");
        request.setAttribute("login", login);
        request.setAttribute("email", email);

        if (!"".equals(login) && !"".equals(password1) && !"".equals(password2) && !"".equals(email)) {

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
                if (password1.length() <= 6) {
                    request.setAttribute("info", "Пароль слишком короткий мин 7 символов");
                    page = resourceBundle.getString("page.regform");
                    flag += 1;
                }

                if (flag == 0) {
                    String salts = Cryptography.getSalts();
                    String password = Cryptography.setEncriptPasssword(password1, salts);
                    Long idUser = 3L;
                    Role role;

                    role = roleFasade.find(idUser);
                    if (role == null || "".equals(role)) {
                        Role role1 = new Role("ADMIN");
                        Role role2 = new Role("EDITOR");
                        Role role3 = new Role("USER");
                        roleFasade.create(role1);
                        roleFasade.create(role2);
                        roleFasade.create(role3);
                        idUser = 1L;
                    }

                    role = roleFasade.find(idUser);

                    Boolean active = true;
                    String image = "no-image.png";
                    User user = new User(login, password, salts, active, role, email, image);

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
