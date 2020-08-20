/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AuthenticationManager;
import model.ConnectionManager;


@WebServlet(urlPatterns = {"/auth"})
public class Authentication extends HttpServlet {
    
    
    private void handler(boolean n, String email, String password, String mode, HttpServletRequest request, HttpServletResponse response) {
        if(n) {
            
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("mode", mode);
            
            try {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile");
                rd.forward(request, response);
            } catch(IOException e) {
                System.err.println(e.getMessage());
            } catch(ServletException e) {
                System.err.println(e.getMessage());
            }
            
        } else {
              try {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/auth-html.html");
                rd.forward(request, response);
            } catch(IOException e) {
                System.err.println(e.getMessage());
            } catch(ServletException e) {
                System.err.println(e.getMessage());
            }          
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
            
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mode = request.getParameter("mode");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        AuthenticationManager am = new AuthenticationManager();
        
        if(mode.equals("sign_in")) {
            handler(am.doesUserExists(email, password), email, password, mode, request, response);
        } else if(mode.equals("sign_up")) {
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            boolean s = am.putUser(firstName, lastName, email, password);
            handler(s, email, password, mode, request, response);
        }
    }

}
