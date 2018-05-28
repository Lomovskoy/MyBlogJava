package controller;

import entity.Comment;
import entity.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CommentFacade;

/**
 * Сервлет отвечающий за лайки комментария
 * @author Lomovskoy
 */
@WebServlet(name = "LikeComServlet", urlPatterns = {"/like-com"})
public class LikeComServlet extends HttpServlet {

    @EJB CommentFacade commentFasade;
    
    /**
     * Метод отвечающий за прибавление и удаление лайков к комментарию
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
        
        String idstring = request.getParameter("id");//?
        String chtoSdelatStr = request.getParameter("choSdelat");//?
        
        long comId = Long.parseLong(idstring);
        boolean stavitLike = "1".equals(chtoSdelatStr);
        
        Comment comment = commentFasade.find(comId);
        
        if(comment == null) return;
        if(user == null) return;
        
        if(stavitLike && !comment.LikedByUser(user)){
            comment.LikeAdd(user);
        } else if(!stavitLike && comment.LikedByUser(user)){
            comment.LikeDelete(user);
        }
        
        commentFasade.edit(comment);
        
        response.getWriter().write((comment.LikedByUser(user) ? "1" : "0") + "," + comment.LikesCount());
        
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
