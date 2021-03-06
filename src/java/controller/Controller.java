package controller;

import command.ActionCommand;
import factory.ActionFactory;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет контроллер шаблона MVC - получающий запрос, обрабатывает его с 
 * помощю интерфейса ActionCommand и отправляет полученную страницу клиенту.
 * @author Lomovskoy
 */
@WebServlet(name = "Controller", urlPatterns = {""})
public class Controller extends HttpServlet {

    /**
     * Метод обработки запросов GET, POST отдаёт нужную страницу 
     * определяемую фабрикой и нужным коммандом
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException ошибка при обработке сервлета.
     * @throws IOException математическая ошибка вычисления.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        ActionFactory factory = new ActionFactory();
        ActionCommand actionCommand = factory.defineCommand(request);
        String page = actionCommand.execute(request);

        if (page != null) {
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            request.setAttribute("error", "Error! Обращение к несуществующей странице");
            ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
            page = resourceBundle.getString("page.error");
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
