package command.admin;

import command.ActionCommand;
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
import session.UserFacade;

/**
 * Класс менеджер пользователя: определяющий анждминистратор или юзер
 * @author Lomovskoy
 */
public class UserManagementCommand implements ActionCommand{

    private UserFacade userFasade;
    
    /**
     * Конструктор реализующий подключение нужного бина
     * в контекте этого класса.
     */
    public UserManagementCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.userFasade = (UserFacade) context.lookup("java:module/UserFacade");
        }catch(NamingException ex){
            Logger.getLogger(AdminCommand.class.getName()).log(Level.SEVERE,"Не удалось сессионный бин ",ex);
        }
    }
    
    /**
     * Метод определяющий администратор пользователя или 
     * юзер и перенаправляет его на соответствующую страницу
     * при входе на найт.
     * @param request
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.index");
        
        if(session.getAttribute("admin").equals(true)){
            List<User>users = this.userFasade.findAll();
            request.setAttribute("users", users);
            page = resourceBundle.getString("page.usermanagementform");
        }

        return page;
    }
    
    
}
