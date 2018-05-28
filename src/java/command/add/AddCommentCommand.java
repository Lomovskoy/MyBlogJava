package command.add;

import command.ActionCommand;
import entity.Article;
import entity.Comment;
import entity.User;
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
 * Класс отвечающий за добавление комментария от пользователя
 * @author Lomovskoy
 */
public class AddCommentCommand implements ActionCommand{
    
    private ArticleFacade articleFasade;
    
    /**
     * Конструктор реализующий поиск в контексте сервера придложений и 
     * подключение нужного бина в поле класса.
     */
    public AddCommentCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
        }catch(NamingException ex){
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE,"Не удалось сессионный бин ",ex);
        }
    }
    
    /**
     * Метод отвечающий за добавление комментария от пользователя
     * @param request HttpServletRequest
     * @return String
     */
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

            Comment comment = new Comment(user, commentStr, publicdate.getTime(), new ArrayList<>());
            article.getComments().add(comment);
            articleFasade.edit(article);
            request.setAttribute("redirect", "?page=showOneArticle&id="+article.getId());
            
        }else{
            Article article = articleFasade.find(articleId);
            request.setAttribute("article", article);
            request.setAttribute("info", "Строка комментария должна быть не пустой <br>"
                    + "или его длинна должна быть меньше 2000 символов");
        }      
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.onearticle");
        
        return page;
    }
    
}
