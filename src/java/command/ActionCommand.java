package command;

import javax.servlet.http.HttpServletRequest;

/**
 * Интерфейс позволяющий реализовать request обычным классам
 * @author Lomovskoy
 */
public interface ActionCommand {

    /**
     * Метод возвращающий request execute Выполняет заданную инструкцию, 
     * которая может вернуть несколько результатов
     */
    String execute(HttpServletRequest request);
}
