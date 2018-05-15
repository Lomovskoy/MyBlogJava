package controller;

import command.add.UpdateFileFormCommand;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import resours.FileDirectoriesManager;

/**
 * Сервлет отвечающи за закгрузки кизображений для статей
 * @author Lomovskoy
 */
@WebServlet(name = "FileUploadController", urlPatterns = {"/upload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    private final static Logger LOGGER
            = Logger.getLogger(FileUploadServlet.class.getCanonicalName());

    /**
     * Обрабатывает запросы для HTTP <code> GET </ code> и <code> POST </ code>
     * методы.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException если возникает ошибка, зависящая от сервлета
     * @throws IOException если возникает ошибка ввода-вывода
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        final String path = request.getServletContext().getRealPath("") + File.separator
                + FileDirectoriesManager.getProperty("dir") + File.separator
                + FileDirectoriesManager.getProperty("files");
        final Part filePart = request.getPart("file");
        final String fileName = getNewFileName(request, getFileName(filePart));

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
            ufc.execute(request);
        }
        response.sendRedirect("?page=addfile");
    }

    /**
     * Метод получения имени файла
     * @param part
     * @return String
     */
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

    /**
     * Метод получения нового имени вайла
     * @param request
     * @param oldFileName
     * @return String
     */
    private String getNewFileName(HttpServletRequest request, String oldFileName) {
        String[] splitByTochka = oldFileName.split(Pattern.quote("."));
        String fileType = splitByTochka[splitByTochka.length - 1];
        
        if ("jpg".equals(fileType)) {
            String path = request.getServletContext().getRealPath("")+File.separator
                +FileDirectoriesManager.getProperty("dir")+File.separator
                    +FileDirectoriesManager.getProperty("files");
        
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();
            Integer filename = (listOfFiles.length + 1);
            
            String newfilename = Integer.toString(filename);
            return newfilename + "." + fileType;
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
