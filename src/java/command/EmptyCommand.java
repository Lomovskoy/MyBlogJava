package command;

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
import session.ArticleFacade;

/**
 *
 * @author pupil Класс пустой команды
 */
public class EmptyCommand implements ActionCommand {

    private ArticleFacade articleFasade;

    public EmptyCommand() {
        Context context;
        try {
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE, "Не удалось сессионный бин ", ex);
        }
    }

    /**
     *
     * @param request
     * @return Метод возвращающий индексную страницу если нет заданных
     * переметров сслки
     */
    @Override
    public String execute(HttpServletRequest request) {

        try {
            /*List<Article> articles = articleFasade.findAll();
            Collections.reverse(articles);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            request.setAttribute("dateFormat", dateFormat);
            request.setAttribute("articles", articles);*/

            Integer articlesOnPage = 10;
            Integer pageCurrent = 1;
            String currentPageString = request.getParameter("pagination");
            if (currentPageString != null) {
                pageCurrent = Integer.parseInt(currentPageString);
            }
            Integer pageCount = (int) Math.ceil((double) articleFasade.count() / articlesOnPage);
            Integer[] pageArray = new Integer[pageCount];
            for (Integer i = 0; i < pageArray.length; i++) {
                pageArray[i] = i + 1;
            }
            Integer articleFrom = articlesOnPage * (pageCurrent - 1);
            Integer articleTo = (articlesOnPage * pageCurrent);
            
            List<Article> articles = articleFasade.findPagination(articleFrom, articleTo);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            request.setAttribute("dateFormat", dateFormat);
            request.setAttribute("articles", articles);
            request.setAttribute("pages", pageArray);
            
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
