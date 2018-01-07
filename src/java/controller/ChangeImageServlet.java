
package controller;

import command.UpdateFileFormCommand;
import entity.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import resours.FileDirectoriesManager;
import session.UserFacade;

/**
 *
 * @author lomov
 */
@WebServlet(name = "ChangeImage", urlPatterns = {"/changeimage"})
@MultipartConfig
public class ChangeImageServlet extends HttpServlet {

    private final static Logger LOGGER
            = Logger.getLogger(FileUploadServlet.class.getCanonicalName());
    //private UserFacade userFasade;
    @EJB UserFacade userFasade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path = request.getServletContext().getRealPath("") + File.separator
                + FileDirectoriesManager.getProperty("dir") + File.separator
                + FileDirectoriesManager.getProperty("image");
        Part filePart = request.getPart("image");
        String fileName = (String) getFileName(filePart);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (fileName != null) {
            OutputStream out = null;
            InputStream filecontent = null;
            try {
                out = new FileOutputStream(new File(path + File.separator
                        + fileName));
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }

                LOGGER.log(Level.INFO, "Файл {0} начал загружаться в {1}",
                        new Object[]{fileName, path});

                //-------------------------------------
                String name = user.getImage();
                
                if (!"no-image.png".equals(name)){
                String filePath = request.getServletContext().getRealPath("") + File.separator
                        + FileDirectoriesManager.getProperty("dir") + File.separator
                        + FileDirectoriesManager.getProperty("image");

                File file = new File(filePath + '/' + name);
                file.delete();

                path = request.getServletContext().getRealPath("") + File.separator
                        + FileDirectoriesManager.getProperty("dir") + File.separator
                        + FileDirectoriesManager.getProperty("image");

                File folder = new File(path);
                File[] listOfFiles = folder.listFiles();
                request.setAttribute("images", listOfFiles);
                }
                //------------------
                user.setImage(fileName);
                userFasade.edit(user);
                session.setAttribute("user", user);
                

            } catch (FileNotFoundException fne) {
                LOGGER.log(Level.SEVERE, "Проблемы загрузки файла. Error: {0}",
                        new Object[]{fne.getMessage()});
            } finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }

            }

            UpdateFileFormCommand ufc = new UpdateFileFormCommand();
            //!!!!
            ufc.execute(request);
        }
        response.sendRedirect("?page=changeinformationform");
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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
