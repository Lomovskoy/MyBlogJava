package command;

import entity.Article;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import session.ArticleFacade;

/**
 * Класс отвечающий за поиск и отображение статей
 * @author Lomovskoy
 */
public class SearchCommand implements ActionCommand {

    private ArticleFacade articleFasade;

    /**
     * Конструктор реализующий поиск в контексте сервера придложений и 
     * подключение нужного бина в поле класса.
     */
    public SearchCommand() {
        Context context;
        try {
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE, "Не удалось сессионный бин ", ex);
        }
    }

    /**
     * Метод осуществляющий поиск по статьям и отображение найденных
     * @param request HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        try {

            String search = request.getParameter("search");
            search = search.trim();
            if (!search.equals("")) {

                List<Article> articles = articleFasade.searchPagination(search);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                request.setAttribute("dateFormat", dateFormat);
                request.setAttribute("articles", articles);
                request.setAttribute("resultSearch", articles.size());
            }

        } catch (Exception e) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
            String page = resourceBundle.getString("page.index");
            return page;
        }

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.index");
        return page;
    }

}
