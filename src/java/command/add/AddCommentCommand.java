
package command.add;

import command.ActionCommand;
import entity.Article;
import entity.Comment;
import entity.User;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import session.ArticleFacade;
import session.CommentFacade;

/**
 *
 * @author pupil
 */
public class AddCommentCommand implements ActionCommand{
    
    private ArticleFacade articleFasade;
    private CommentFacade commentFacade;
    
    public AddCommentCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.commentFacade = (CommentFacade) context.lookup("java:module/CommentFacade");
        }catch(NamingException ex){
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE,"Не удалось сессионный бин ",ex);
        }
    }
    
    @Override
    public String execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        
        String Id = (String) request.getParameter("articleid");
        Long articleId = Long.parseLong(Id);
        String commentStr = (String) request.getParameter("comment");
        
        if (!"".equals(commentStr) &&  commentStr.length() <= 2000){
            User user = (User) session.getAttribute("user");
            Calendar publicdate = new GregorianCalendar();
            Article article = articleFasade.find(articleId);

            Comment comment = new Comment(user, commentStr, publicdate.getTime());
            article.getComments().add(comment);
            articleFasade.edit(article);
            request.setAttribute("redirect", "?page=showOneArticle&id="+article.getId());
        }
        else{
            Article article = articleFasade.find(articleId);
            request.setAttribute("article", article);
            request.setAttribute("info", "Строка комментария должна быть не пустой <br>или его длинна должна быть меньше 2000 символов");
        }      
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.onearticle");
        
        return page;
    }
    
}
