package factory;

import command.ActionCommand;
import command.CommandEnum;
import command.EmptyCommand;
import command.ErrorCommand;
import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    public ActionFactory() {
    }
    
    public ActionCommand defiCommand(HttpServletRequest request){
        
        ActionCommand current = new EmptyCommand();
        String command = request.getParameter("command");
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
