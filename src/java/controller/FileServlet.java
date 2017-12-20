package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import resours.FileDirectoriesManager;

/**
 *
 * @author pupil
 */
@WebServlet(name = "fileServlet", urlPatterns = {"/fileServlet/*"})
public class FileServlet extends HttpServlet {

    // Constants ----------------------------------------------------------------------------------

    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

    // Properties ---------------------------------------------------------------------------------

    //private String filePath;

    // Actions ------------------------------------------------------------------------------------

    @Override
    public void init() throws ServletException {
        //
        // Define base path somehow. You can define it as init-param of the servlet.
        //this.filePath = "../../../../my_blog_lomovskoy_image/files/";
        //this.filePath = "..\\..\\..\\..\\my_blog_lomovskoy_image\\files\\";
        //this.filePath = "C:\\images";
        //this.filePath = "D:\\JKTVR-16\\Lomovskoy\\my_blog_lomovskoy_image\\files\\";
        // In a Windows environment with the Applicationserver running on the
        // c: volume, the above path is exactly the same as "c:\files".
        // In UNIX, it is just straightforward "/files".
        
    }
    
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
        //Эта строчка говорит программе считать путь от папки с программой
        //Если её нету то в конструкторе нужно прописывать обсалютный 
        //путь так как путь будет считаться от сервера
        String filePath = request.getServletContext().getRealPath("")+File.separator
                +FileDirectoriesManager.getProperty("dir")+File.separator
                    +FileDirectoriesManager.getProperty("files");
        // Путь к запрашиваемому файлу.
        String requestedFile = request.getPathInfo();

        // Проверим, действительно ли передан путь в запросе.
        if (requestedFile == null) {
            // Здесь можно послать сообщение о случившемся.
            // Бросить исключение, или послать 404, или показать страницу ошибки, 
            // или просто игнорировать ошибку.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        
        // Создадим объект типа File, при этом декодируем имя файла полученного из request
        // т.к оно может содержать пробелы и другие символы
        File file = new File(filePath, URLDecoder.decode(requestedFile, "UTF-8"));
        // Check if file actually exists in filesystem.
        // Проверим, существует ли файл в файловой системе
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        
        // Получим тип контента по имени файла
        String contentType = getServletContext().getMimeType(file.getName());

        
        //Если тип контента неизвестен, установите значение по умолчанию.
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        // Init servlet response.
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        // Prepare streams.
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            // Open streams.
            input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            // Write file contents to response.
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } finally {
            // Gently close streams.
            close(output);
            close(input);
        }

    }
    // Helpers (can be refactored to public utility class) ----------------------------------------

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it.
                e.getMessage();
            }
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
