package command;
/**
 * Класс позволяющий передавать request обычным классам
 */
import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @author pupil
 * Метод возвращающий request 
 * execute Выполняет заданную инструкцию, которая 
 * может вернуть несколько результатов
 */
public interface ActionCommand {
    String execute(HttpServletRequest request);
}