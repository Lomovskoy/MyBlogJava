package command.update;

import command.ActionCommand;
import command.reg_login.LoginCommand;
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
 * Класс отвечающий за изменение статьи
 * @author Lomovskoy
 */
public class UpdateArticleCommand implements ActionCommand{

    private ArticleFacade articleFasade;
    
    /**
     * Конструктор реализующий подключение нужного бина
     * в контекте этого класса.
     */
    public UpdateArticleCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
        }catch(NamingException ex){
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE,"Не удалось сессионный бин ",ex);
        }
    }
    
    /**
     * Метод отвечающий за изменение статьи
     * @param request
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);       
        User user = (User) session.getAttribute("user");
        
        String Id = request.getParameter("id");
        Long articleId = Long.parseLong(Id);
        Article article = articleFasade.find(articleId);
        
        String caption = (String) request.getParameter("caption");
        String content = (String) request.getParameter("content");
        String activeS = (String) request.getParameter("active");
        Boolean active = true;
        
        if(activeS.equals("0")){
            active = false;
        }
        
        article.setCaption(caption);
        article.setContent(content);
        article.setActive(active);
                
        articleFasade.edit(article);
        
        LoginCommand logCom = new LoginCommand();
        logCom.execute(request);
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.adminpage");
        return page;
        
    }
    
    
    
}
