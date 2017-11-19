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
import javax.servlet.http.HttpSession;
import session.AdminFacade;

/**
 *
 * @author pupil
 * Класс проверяющий вход в кабинет администратора
 */
public class LoginCommand implements ActionCommand{
    
    private AdminFacade adminFasade;
    
    public LoginCommand() {
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
        
        HttpSession session = request.getSession(false);
        
        
        String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");
        Admin admin = this.adminFasade.findByLogin(login);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.adminlogin");
        
        if (session.getAttribute("admin") == null){
            if (admin != null && Cryptography.comparePasssword(password, admin.getPassword(), admin.getSalts())){
                session.setAttribute("admin", admin);
                request.setAttribute("info", "Вход произведён");
                page = resourceBundle.getString("page.adminpage");
            } 
            else{
                request.setAttribute("info", "Логин или пароль неверны");    
            }
        }
        else{ 
            page = resourceBundle.getString("page.adminpage");
        }
        return page;
        
    }
    
    
}
