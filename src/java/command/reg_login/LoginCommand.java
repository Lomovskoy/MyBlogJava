package command;

import classes.Cryptography;
import entity.User;
import entity.Article;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import session.UserFacade;
import session.ArticleFacade;
import session.RoleFacade;

/**
 *
 * @author pupil Класс проверяющий вход в кабинет администратора
 */
public class LoginCommand implements ActionCommand {

    private UserFacade userFasade;
    private ArticleFacade articleFasade;
    private RoleFacade roleFasade;

    public LoginCommand() {
        Context context;
        try {
            context = new InitialContext();
            this.userFasade = (UserFacade) context.lookup("java:module/UserFacade");
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.roleFasade = (RoleFacade) context.lookup("java:module/RoleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AdminCommand.class.getName()).log(Level.SEVERE, "Не удалось сессионный бин ", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");
        User user = this.userFasade.findByLogin(login);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.adminlogin");

        List<Article> articles = articleFasade.findAll();
        Collections.reverse(articles);
        request.setAttribute("articles", articles);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        request.setAttribute("dateFormat", dateFormat);
        request.setAttribute("articles", articles);
        
        if (session.getAttribute("user") == null) {
            if ((user != null) && (Cryptography.comparePasssword(password, user.getPassword(), user.getSalts()))) {
                
                if (user.getActive() || user.getRole().getRoles().equals("ADMIN")) {
                    session.setAttribute("user", user);
                    request.setAttribute("info", "Вход произведён");

                    if (user.getRole().getRoles().equals("ADMIN") || user.getRole().getRoles().equals("EDITOR")) {
                        page = resourceBundle.getString("page.adminpage");
                        session.setAttribute("admin", true);
                    }
                    if (user.getRole().getRoles().equals("USER")) {
                        page = resourceBundle.getString("page.index");
                        session.setAttribute("admin", false);
                        return page;
                    }
                }else{
                    request.setAttribute("info", "Вы заблокированны");
                }
            } else {
                request.setAttribute("info", "Логин или пароль неверны");
            }
        } else {
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            if (session.getAttribute("admin").equals(true)) {
                page = resourceBundle.getString("page.adminpage");
            } else {
                page = resourceBundle.getString("page.index");
            }
        }
        
        return page;

    }

}
