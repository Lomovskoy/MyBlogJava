package command.add;

import command.ActionCommand;
import command.reg_login.LoginCommand;
import entity.User;
import entity.Article;
import java.util.ArrayList;
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
 * Класс отвечающий за добавлений новой статьи
 * @author Lomovskoy
 */
public class AddArticleCommand implements ActionCommand{

    private ArticleFacade articleFasade;
    
    /**
     * Конструктор реализующий подключение нужного бина
     * в контекте этого класса.
     */
    public AddArticleCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
        }catch(NamingException ex){
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE,"Не удалось сессионный бин ",ex);
        }
    }
    
    /**
     * Метод отвечающий за добавлений новой статьи
     * @param request
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        
        User user = (User) session.getAttribute("user");
        String caption = (String) request.getParameter("caption");
        String content = (String) request.getParameter("content");
        String activeS = (String) request.getParameter("active");
        Boolean active = true;
        if(activeS.equals("0")){
            active = false;
        }
        
        Calendar date = new GregorianCalendar();
        Article article = new Article(caption, content, date.getTime(), user, 
                active, new ArrayList<>(), new ArrayList<>());
        articleFasade.create(article);
        
        LoginCommand logCom = new LoginCommand();
        logCom.execute(request);
        request.setAttribute("redirect", "?page=login");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.adminpage");
        return page;
    }
    
}
