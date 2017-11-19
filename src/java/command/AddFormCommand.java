package command;

import entity.Article;
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
 * @author pupil
 * Класс перехода на страницу добавления статьи
 */
public class AddFormCommand implements ActionCommand{
    private ArticleFacade articleFasade;
    
    public AddFormCommand() {
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
        
        Article article = new Article();
        article = (Article) articleFasade.findAll();
        //Законечил здесь
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.addarticlepage");
        return page;
    }
}
