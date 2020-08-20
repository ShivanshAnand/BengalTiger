/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DbUtils;
import pojo.Form;
import pojo.User;

/**
 *
 * @author hp
 */
@WebServlet(urlPatterns = {"/profile"})
public class Profile extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        DbUtils db = new DbUtils();
        
        User u = db.getUser(email, password);
        
        printHtml(request, response, u);
        
    }
    
    
    private void printHtml(HttpServletRequest request, HttpServletResponse response, User u) throws IOException {
        
        PrintWriter out = response.getWriter();
        
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
        out.println("<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>");
        out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>");
        out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>");
        
        out.println("</head>");
        out.println("<body style=\" margin: 16px \">");
        out.println("<h4>");
        out.println("Welcome " + u.getFirstName() + " " + u.getLastName());
        out.println("</h4>");
        out.println("<form method=\"post\" action=\"new-form\">");
        out.println("<input type=\"text\" name=\"title\" placeholder = \"Enter name of the form \" required />");
        out.println("<input type=\"hidden\" name=\"uid\" value=\"" + u.getUid() + "\" />" );
        out.println("<input type=\"hidden\" name=\"email\" value=\"" + u.getEmail()+ "\" />" );
        out.println("<input type=\"hidden\" name=\"password\" value=\"" + u.getPassword()+ "\" />" );
        out.println("<input type=\"submit\" />");
        out.println("</form>");
        out.println("<h4> Your previous forms </h4>");
        
        ArrayList<Form> arr = new DbUtils().getForm(u.getUid());
        
        out.println("<ol>");
        
        for(Form f : arr) {
            out.println("<li>");
            out.println("<b>" + f.getTitle() + "<u>" + f.getFid() + "</u></b>");
            out.println("<a href=\"form-builder.jsp?title=" +  f.getTitle() + "&uid=" + f.getUid() + "&fid=" + f.getFid() + "\"" + "target=\"_blank\">");
            out.println("Edit");
            out.println("</a>");
            out.println("</li>");
        }
        
        out.println("</ol>");
        
        out.println("</body></html>");
        
    }


}
