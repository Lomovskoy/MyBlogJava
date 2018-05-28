package factory;

import command.ActionCommand;
import command.CommandEnum;
import command.EmptyCommand;
import command.ErrorCommand;
import javax.servlet.http.HttpServletRequest;

/**
 * Класс фабрики, отвечающий за возврат обьекта созданного по команде.
 * (реальзация шаблона "фабричный метод")
 * @author Lomovskoy
 */
public class ActionFactory {

    /**
     * Пустой конструктор
     */
    public ActionFactory() {
    }
    
    /**
     * Метод возврата обьекта созданного по команде.
     * @param request HttpServletRequest 
     * @return Object
     */
    public ActionCommand defineCommand(HttpServletRequest request){
        
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
            return new ErrorCommand();
        }
    }
}
