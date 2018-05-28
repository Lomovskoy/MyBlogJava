package command;

import javax.servlet.http.HttpServletRequest;

/**
 * Интерфейс позволяющий создавать классы с методом 
 * наполняющим контекст формирования страниц данными
 * @author Lomovskoy
 */
public interface ActionCommand {

    /**
     * Метод возвращающий request execute Выполняет заданную инструкцию, 
     * которая может вернуть несколько результатов
     * @param request HttpServletRequest
     * @return String
     */
    String execute(HttpServletRequest request);
}
