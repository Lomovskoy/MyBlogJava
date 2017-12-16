package command;

import classes.Cryptography;
import entity.Article;
import entity.Role;
import entity.User;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class RegistrationCommand implements ActionCommand{

    private UserFacade userFasade;
    private RoleFacade roleFasade;
    
    public RegistrationCommand() {
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
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.adminlogin");
        
        String login = (String) request.getParameter("login");
        String password1 = (String) request.getParameter("password1");
        String password2 = (String) request.getParameter("password2");
        
        if (login != "" && password1 != "" && password2 != ""){

            if (password1.equals(password2)){
                
                String salts = Cryptography.getSalts();
                String password = Cryptography.setEncriptPasssword(password1, salts);
                Long idUser = 2L;
                Role role = roleFasade.find(idUser);
                /*
                List<Role> roles = (List<Role>) roleFasade.findAll();
                for (Role role : roles) {
                    if(role.getRoles()=="USER"){
                        
                    }
                }*/
                Boolean active = true;
                User user = new User(login, password, salts, active, role);
                
                userFasade.create(user);
                
                request.setAttribute("info", "Вы зарегистрированны");
            }else{
                request.setAttribute("info", "Пароли не совпадают");
            }
        }else{
            request.setAttribute("info", "Заполните все поля пожалуйста");
        }
        return page;
    }
    
}
