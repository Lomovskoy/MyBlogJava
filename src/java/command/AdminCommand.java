package command;

import classes.Cryptography;
import entity.Admin;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import session.AdminFacade;

/**
 *
 * @author pupil
 * Класс рабты с администратором
 */
public class AdminCommand implements ActionCommand{
    
    private AdminFacade adminFasade;
    
    public AdminCommand() {
        Context context;
        try{
            context = new InitialContext();
            this.adminFasade = (AdminFacade) context.lookup("java:module/AdminFacade");
        }catch(NamingException ex){
            Logger.getLogger(AdminCommand.class.getName()).log(Level.SEVERE,"Не удалось сессионный бин ",ex);
        }
    }
    
    @Override
    public String execute(HttpServletRequest request) {  
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.adminlogin");
        return page;
    }
    
}
