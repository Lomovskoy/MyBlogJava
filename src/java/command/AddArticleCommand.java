package command;

import entity.Admin;
import entity.Article;
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

/**
 *
 * @author pupil
 */
public class AddArticleCommand implements ActionCommand{

    private ArticleFacade articleFasade;
    
    public AddArticleCommand() {
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
        
        HttpSession session = request.getSession(false);
        String path = request.getServletPath();
        
        Admin admin = (Admin) session.getAttribute("admin");
        String caption = (String) request.getParameter("caption");
        String content = (String) request.getParameter("content");
        Calendar date = new GregorianCalendar(); //date.getTime()
        Article article = new Article(caption, content, date.getTime(), admin);
        articleFasade.create(article);
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.adminpage");
        return page;
    }
    
}
