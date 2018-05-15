package factory;

import command.ActionCommand;
import command.CommandEnum;
import command.EmptyCommand;
import command.ErrorCommand;
import javax.servlet.http.HttpServletRequest;

/**
 * Класс фабрики, отвечающий за возврат нужной страницы для запроса
 * @author Lomovskoy
 */
public class ActionFactory {

    public ActionFactory() {
    }
    
    /**
     * Метод возврата нужной страницы на запрос
     * @param request
     * @return Object
     */
    public ActionCommand defiCommand(HttpServletRequest request){
        
        ActionCommand current = new EmptyCommand();
        String command = request.getParameter("page");
        if(command == null || command.isEmpty()){
            return current;
        }
        try{
            CommandEnum commandEnum = CommandEnum.valueOf(command.toUpperCase());
            current = commandEnum.getCurrentCommand();
            return current;
        }catch(Exception e){
            request.setAttribute("error", "Стрраницы \""+command+"\" не существует!");
            return current = new ErrorCommand();
        }
    }
}
