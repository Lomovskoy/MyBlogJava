/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author imxo
 */
public class ChangeInformationFormCommand implements ActionCommand {
    
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resours.config");
        String page = resourceBundle.getString("page.index");
        if (session.getAttribute("user") != null) {
            page = resourceBundle.getString("page.information");
        }
        return page;
    }

}
