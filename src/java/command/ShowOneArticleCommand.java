package command;

import entity.User;
import entity.Article;
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
 *
 * @author pupil
 */
public class ShowOneArticleCommand implements ActionCommand{

     private ArticleFacade articleFasade;
    
    public ShowOneArticleCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
        }catch(NamingException ex){
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE,"Не удалось сессионный бин ",ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        
        String Id = request.getParameter("id");
        Long articleId = Long.parseLong(Id);
        Article article = articleFasade.find(articleId);
        
        request.setAttribute("article", article);
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.onearticle");
        return page;
    }
    
}