package command.dell;

import command.ActionCommand;
import entity.Article;
import entity.Comment;
import entity.User;
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
 * Клас отвечающий за удаления комментария
 * @author Lomovskoy
 */
public class DellCommentCommand implements ActionCommand{

    private ArticleFacade articleFacade;
    private CommentFacade commentFacade;
    
    /**
     * Конструктор реализующий поиск в контексте сервера придложений и 
     * подключение нужного бина в поле класса.
     */
    public DellCommentCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.articleFacade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.commentFacade = (CommentFacade) context.lookup("java:module/CommentFacade");
        }catch(NamingException ex){
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE,"Не удалось сессионный бин ",ex);
        }
    }
    
    /**
     * Метод удаления комментария
     * @param request HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        
        String articleIdString = (String) request.getParameter("articleid");
        Long articleId = Long.parseLong(articleIdString);
        
        String commentIdString = (String) request.getParameter("commentid");
        Long commentId = Long.parseLong(commentIdString);
        
        Comment comment = commentFacade.find(commentId);
        Article article = articleFacade.find(articleId);
        
        if(comment.getAuthor().equals(user) || user.getRole().getRoles().equals("ADMIN")){   
            article.getComments().remove(comment);
            articleFacade.edit(article);
            commentFacade.remove(comment);
        }
        
        request.setAttribute("redirect", "?page=showOneArticle&id="+article.getId());
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.onearticle");
        return page;
    }
    
}
