/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DbUtils;

/**
 *
 * @author hp
 */
@WebServlet(urlPatterns = {"/new-form"})
public class NewForm extends HttpServlet {

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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uid = request.getParameter("uid");
        String title = request.getParameter("title");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        boolean x = new DbUtils().createNewForm(Integer.parseInt(uid), title);
        
        if(x) {
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile");
            rd.forward(request, response);
            
        } else {
            response.getWriter().println("Cannot make your form RN, try later");
        }

    }

}
