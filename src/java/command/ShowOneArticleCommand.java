package command;

import entity.Article;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import session.ArticleFacade;

/**
 * Класс отвечающий за отображение одной статьи
 * @author Lomovskoy
 */
public class ShowOneArticleCommand implements ActionCommand {

    private ArticleFacade articleFasade;

    /**
     * Конструктор реализующий подключение нужного бина
     * в контекте этого класса.
     */
    public ShowOneArticleCommand() {
        Context context;
        try {
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE, "Не удалось сессионный бин ", ex);
        }
    }

    /**
     * Метод отвечающий за отображение одной статьи
     * @param request
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        String Id = request.getParameter("id");
        Long articleId = Long.parseLong(Id);
        Article article = articleFasade.find(articleId);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.onearticle");
        
        if (article.getActive().equals(true)) {
            request.setAttribute("article", article);
            Collections.reverse(article.getComments());
            request.setAttribute("comments", article.getComments());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            request.setAttribute("dateFormat", dateFormat);          
        }else{
            page = resourceBundle.getString("page.index");
        }
        return page;
    }

}
