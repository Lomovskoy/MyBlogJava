
package command.update;

import command.ActionCommand;
import command.admin.AdminCommand;
import command.admin.UserManagementCommand;
import entity.Role;
import entity.User;
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
public class UpdateUserCommand implements ActionCommand{
    
    private UserFacade userFasade;
    private RoleFacade roleFacade;
    
    public UpdateUserCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.userFasade = (UserFacade) context.lookup("java:module/UserFacade");
            this.roleFacade = (RoleFacade) context.lookup("java:module/RoleFacade");
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
             
            
            String active = (String) request.getParameter("active");
            String roleStr = (String) request.getParameter("role");
            
            //boolean activebool = Boolean.parseBoolean(active);
            if(active != null && roleStr != null && active != "" && roleStr != ""){
                
                Role role = roleFacade.find(Long.parseLong(roleStr));

                user.setActive("true".equals(active));
                user.setRole(role);

                userFasade.edit(user);
                
                return new UserManagementCommand().execute(request);
            }
            request.setAttribute("user", user);

            page = resourceBundle.getString("page.updateuserform");
        }

        return page;
    }
    
}
            
