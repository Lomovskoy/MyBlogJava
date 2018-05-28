package command;

import command.admin.AdminCommand;
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
import session.ArticleFacade;

/**
 * Класс отвечающий за отображение всех статей с пагинацией.
 * @author Lomovskoy
 */
public class ShowArticlesCommand implements ActionCommand {

    private ArticleFacade articleFasade;

    /**
     * Конструктор реализующий поиск в контексте сервера придложений и 
     * подключение нужного бина в поле класса.
     */
    public ShowArticlesCommand() {
        Context context;
        try {
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(AdminCommand.class.getName()).log(Level.SEVERE, "Не удалось сессионный бин ", ex);
        }
    }

    /**
     * Метод отвечающий за отображение всех статей с пагинацией.
     * @param request HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.shouarticles");
        if (session.getAttribute("admin").equals(true)) {
            try {
                List<Article> articles = articleFasade.findAll();
                Collections.reverse(articles);
                request.setAttribute("articles", articles);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                request.setAttribute("dateFormat", dateFormat);
                request.setAttribute("articles", articles);
            } catch (Exception e) {
                request.setAttribute("info", "Статей нет.");
            }
        } else {
            page = resourceBundle.getString("page.index");
        }
        return page;
    }

}
