package command;

import entity.Article;
import entity.Comment;
import java.text.DecimalFormat;
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
import session.CommentFacade;

/**
 *
 * @author pupil
 */
public class ShowOneArticleCommand implements ActionCommand {

    private ArticleFacade articleFasade;
    private CommentFacade commentFacade;

    public ShowOneArticleCommand() {
        Context context;
        try {
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.commentFacade = (CommentFacade) context.lookup("java:module/CommentFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE, "Не удалось сессионный бин ", ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {

        String Id = request.getParameter("id");
        Long articleId = Long.parseLong(Id);
        Article article = articleFasade.find(articleId);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.onearticle");
        
        if (article.getActive().equals(true)) {
            request.setAttribute("article", article);
            List<Comment> commentDB = commentFacade.findById(article);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            request.setAttribute("dateFormat", dateFormat);
            request.setAttribute("comments", commentDB);
        }else{
            page = resourceBundle.getString("page.index");
        }
        return page;
    }

}
