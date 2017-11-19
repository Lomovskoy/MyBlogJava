package command;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @author pupil
 * Класс пустой команды
 */
public class EmptyCommand implements ActionCommand{
    /**
     * 
     * @param request
     * @return
     * Метод возвращающий индексную страницу если нет заданных переметров сслки
     */
    @Override 
    public String execute(HttpServletRequest request){
        
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.index");
        return page;
    }
    
}
