/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import entity.Article;
import entity.Comment;
import entity.User;
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
import session.UserFacade;

/**
 *
 * @author pupil
 */
public class UpdateCommentCommand implements ActionCommand{

     private ArticleFacade articleFasade;
    private UserFacade userFasade;
    private CommentFacade commentFacade;
    
    public UpdateCommentCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
            this.userFasade = (UserFacade) context.lookup("java:module/UserFacade");
            this.commentFacade = (CommentFacade) context.lookup("java:module/CommentFacade");
        }catch(NamingException ex){
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE,"Не удалось сессионный бин ",ex);
        }
    }
    
    @Override
    public String execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        String Id = (String) request.getParameter("articleid");
        User user = (User) session.getAttribute("user");
        Id = (String) request.getParameter("commentid");
        Long commentId = Long.parseLong(Id);
        
        Comment comment = commentFacade.find(commentId);
        Long articleId = comment.getArticle().getId();
        
        
        if(comment.getAuthor().equals(user) || user.getRole().getRoles().equals("ADMIN")){
            
            request.setAttribute("changeComments", comment);
            commentFacade.remove(comment);
        }
        
        Article article = articleFasade.find(articleId);
        request.setAttribute("article", article);
        List<Comment> commentDB = commentFacade.findById(article);
        request.setAttribute("comments", commentDB);
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.onearticle");
        return page;
    }
    
}
