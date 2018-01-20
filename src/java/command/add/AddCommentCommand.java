
package command.add;

import command.ActionCommand;
import command.reg_login.LoginCommand;
import entity.Article;
import entity.Comment;
import entity.User;
import java.text.SimpleDateFormat;
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

            Comment comment = new Comment(user, article, commentStr, publicdate.getTime());
            commentFacade.create(comment);

            LoginCommand logCom = new LoginCommand();
            logCom.execute(request);

            request.setAttribute("article", article);
            List<Comment> commentDB = commentFacade.findById(article);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            request.setAttribute("dateFormat", dateFormat);
            request.setAttribute("comments", commentDB);

        }
        else{
            Article article = articleFasade.find(articleId);
            request.setAttribute("article", article);
            List<Comment> commentDB = commentFacade.findById(article);
            request.setAttribute("comments", commentDB);
            request.setAttribute("info", "Строка комментария должна быть не пустой <br>или его длинна должна быть меньше 2000 символов");
        }      
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.onearticle");
        
        return page;
    }
    
}
