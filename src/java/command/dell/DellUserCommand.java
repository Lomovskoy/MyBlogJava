
package command.dell;

import classes.Cryptography;
import command.ActionCommand;
import command.admin.AdminCommand;
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
 * Класс отвечающий за удаление пользователя
 * @author Lomovskoy
 */
public class DellUserCommand implements ActionCommand{
    
    private UserFacade userFasade;
    
    /**
     * Конструктор реализующий поиск в контексте сервера придложений и 
     * подключение нужного бина в поле класса.
     */
    public DellUserCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.userFasade = (UserFacade) context.lookup("java:module/UserFacade");
        }catch(NamingException ex){
            Logger.getLogger(AdminCommand.class.getName()).log(Level.SEVERE,"Не удалось сессионный бин ",ex);
        }
    }

    /**
     * Метод удаления пользователя
     * @param request HttpServletRequest
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.index");
        
        if(session.getAttribute("admin").equals(true)){

            String Id = request.getParameter("id");
            Long userId = Long.parseLong(Id);
            
            User user = userFasade.find(userId);
            user.setActive(Boolean.FALSE);
            user.setImage("delete.png");
            String password = Cryptography.getSalts();
            user.setPassword(password);
            
            userFasade.edit(user);

            List<User>users = this.userFasade.findAll();
            request.setAttribute("users", users);
            
            page = resourceBundle.getString("page.usermanagementform");
        }

        return page;
    }
    
    
}
