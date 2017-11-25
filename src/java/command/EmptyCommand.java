package command;

import entity.Article;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
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
 * Класс пустой команды
 */
public class EmptyCommand implements ActionCommand{
    
    private ArticleFacade articleFasade;
    
    public EmptyCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
        }catch(NamingException ex){
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE,"Не удалось сессионный бин ",ex);
        }
    }
    /**
     * 
     * @param request
     * @return
     * Метод возвращающий индексную страницу если нет заданных переметров сслки
     */
    @Override 
    public String execute(HttpServletRequest request){
        
        List<Article>articles = articleFasade.findAll();
        Collections.reverse(articles);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        request.setAttribute("dateFormat", dateFormat);
        request.setAttribute("articles", articles);
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.index");
        return page;
    }
}
