package command;

import classes.Cryptography;
import entity.Role;
import entity.User;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import session.RoleFacade;
import session.UserFacade;

/**
 *
 * @author pupil
 * Класс рабты с администратором
 */
public class AdminCommand implements ActionCommand{
    
    private UserFacade userFasade;
    private RoleFacade roleFacade;
    
    public AdminCommand() {
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

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.adminlogin");
        return page;
        
//        String roles1 = "ADMIN";
//        String roles2 = "USER";
//        Role role1obj = new Role(roles1);
//        Role role2obj = new Role(roles2);
//        this.roleFacade.create(role1obj);
//        this.roleFacade.create(role2obj);
//        //----------------------------------------
//        Cryptography cripto = new Cryptography();
//        String login = "admin";
//        String password = "74656554";
//        String salts = cripto.getSalts();
//        String passwordHash = cripto.setEncriptPasssword(password, salts);
//        User user = new User(login, passwordHash, salts, Boolean.TRUE, role1obj);
//        this.userFasade.create(user);
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
//        String page = resourceBundle.getString("page.adminlogin");
//        page = resourceBundle.getString("page.adminpage");
//        return page;
    }
    
}
