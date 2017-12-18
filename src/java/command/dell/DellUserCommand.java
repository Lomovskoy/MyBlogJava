
package command;

import entity.Article;
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
import session.RoleFacade;
import session.UserFacade;

/**
 *
 * @author pupil
 */
public class DellUserCommand implements ActionCommand{
    
    private UserFacade userFasade;
    private RoleFacade roleFasade;
    
    public DellUserCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.userFasade = (UserFacade) context.lookup("java:module/UserFacade");
            this.roleFasade = (RoleFacade) context.lookup("java:module/RoleFacade");
        }catch(NamingException ex){
            Logger.getLogger(AdminCommand.class.getName()).log(Level.SEVERE,"Не удалось сессионный бин ",ex);
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(false);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.index");
        
        if(session.getAttribute("admin").equals(true)){

            String Id = request.getParameter("id");
            Long userId = Long.parseLong(Id);
            
            User user = userFasade.find(userId);
            userFasade.remove(user);

            List<User>users = this.userFasade.findAll();
            request.setAttribute("users", users);
            
            page = resourceBundle.getString("page.usermanagementform");
        }

        return page;
    }
    
    
}