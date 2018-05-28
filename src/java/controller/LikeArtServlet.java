package controller;

import entity.Article;
import entity.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ArticleFacade;

/**
 * Сервлет отвечающий за лайки статьи
 * @author Lomovskoy
 */
@WebServlet(name = "LikeServlet", urlPatterns = {"/like-art"})
public class LikeArtServlet extends HttpServlet {

    @EJB ArticleFacade articleFasade;

    /**
     * Метод отвечающий за прибавление и удаление лайков к статье
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException ошибка работы сервлета.
     * @throws IOException арифметическая ошибка вычисления.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");
        
        String idstring = request.getParameter("id");
        String chtoSdelatStr = request.getParameter("choSdelat");
        
        long artId = Long.parseLong(idstring);
        boolean stavitLike = "1".equals(chtoSdelatStr);
        
        Article article = articleFasade.find(artId);
        
        if(article == null) return;
        if(user == null) return;
        
        if(stavitLike && !article.LikedByUser(user)){
            article.LikeAdd(user);
        } else if(!stavitLike && article.LikedByUser(user)){
            article.LikeDelete(user);
        }
        
        articleFasade.edit(article);
        
        response.getWriter().write((article.LikedByUser(user) ? "1" : "0") + "," + article.LikesCount());
        
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
