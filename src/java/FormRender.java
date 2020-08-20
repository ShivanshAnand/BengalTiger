/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DbUtils;
import pojo.Form;

/**
 *
 * @author hp
 */
@WebServlet(urlPatterns = {"/form-render"})
public class FormRender extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int fid = Integer.parseInt(request.getParameter("fid"));
        
        DbUtils dbu = new DbUtils();
        
        ArrayList<String> arr = dbu.getRenderableForm(fid);
        
        renderForm(arr, request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    public void renderForm(ArrayList<String> arr, HttpServletRequest request, HttpServletResponse response) throws IOException{

        PrintWriter out = response.getWriter();
        
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
        out.println("<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>");
        out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>");
        out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>");
        
        out.println("<style type = \"text/css\">");
        
        
        out.println("body { background-color : #fafafa; }");
        
        out.println("form { " + "box-shadow : 0 4px 8px rgb(184,184,184); padding : 16px; width: 50vw; margin-left: 25vw; box-radius : 4px; background-color: white; }");
             
        out.println(".full-inp{" + "display: inline-block;" + "width: 100%; }");

	out.println("textarea {" + "display: inline-block;" + "width: 100%; }");
	
        out.println("</style>");
        
        out.println("</head>");
        out.println("<body style=\" margin: 16px \">");
        
        out.println("<form method=\"post\" action=\"post\" style=\"margin : 16px 25vw; width : 50vw;\">");
        
        for(int i=0; i<arr.size() - 1; i+=2) {
            out.println(arr.get(i));
            out.println(arr.get(i+1) + "<br />");        
        }
        
        out.println("<br /><input type=\"submit\" >");
        
        out.println("</form>");
        out.println("</body></html>");
        
    }

}
