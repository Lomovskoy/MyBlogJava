package command.dell;

import command.ActionCommand;
import command.reg_login.LoginCommand;
import entity.User;
import entity.Article;
import java.util.Objects;
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
 * Класс отвечающий за удаления Статьи
 * @author Lomovskoy
 */
public class DellArticleCommand implements ActionCommand {

    private ArticleFacade articleFasade;

    /**
     * Конструктор реализующий поиск в контексте сервера придложений и 
     * подключение нужного бина в поле класса.
     */
    public DellArticleCommand() {
        Context context;
        try {
            context = new InitialContext();
            this.articleFasade = (ArticleFacade) context.lookup("java:module/ArticleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ArticleFacade.class.getName()).log(Level.SEVERE, "Не удалось сессионный бин ", ex);
        }
    }

    /**
     * Метод удаления статьи и перенаправляющий на 
     * страницу редактирования статей
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
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.adminpage");
        
        if (("ADMIN".equals(user.getRole().getRoles()) || Objects.equals(user.getId(), article.getAuthor().getId()))) {
            articleFasade.remove(article);
            LoginCommand logCom = new LoginCommand();
            logCom.execute(request);
        }
        request.setAttribute("redirect", "?page=viewarticles");
        return page;
    }
}
