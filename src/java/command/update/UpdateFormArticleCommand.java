
package command.update;

import command.ActionCommand;
import entity.User;
import entity.Article;
import java.io.File;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import resours.FileDirectoriesManager;
import session.ArticleFacade;

/**
 * Класс отвечающий за переход на форму изменения статьи
 * @author Lomovskoy
 */
public class UpdateFormArticleCommand implements ActionCommand{
    
    private ArticleFacade articleFasade;
    
    /**
     * Конструктор реализующий поиск в контексте сервера придложений и 
     * подключение нужного бина в поле класса.
     */
    public UpdateFormArticleCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
        }catch(NamingException ex){
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE,"Не удалось сессионный бин ",ex);
        }
    }
    
    /**
     * Метод перехода на форму изменения комментария
     * @param request HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);       
        User user = (User) session.getAttribute("user");
        
        String Id = request.getParameter("id");
        Long articleId = Long.parseLong(Id);
        Article article = articleFasade.find(articleId);
        request.setAttribute("article", article);
        
        String path = request.getServletContext().getRealPath("")+File.separator
                +FileDirectoriesManager.getProperty("dir")+File.separator
                    +FileDirectoriesManager.getProperty("files");
        
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        
        request.setAttribute("images", listOfFiles);
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.updateform");
        return page;
        
    }
}
