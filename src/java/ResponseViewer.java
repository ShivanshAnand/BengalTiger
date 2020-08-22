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
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import model.DbUtils;
import pojo.Response;

/**
 *
 * @author hp
 */
@WebServlet(urlPatterns = {"/response-viewer"})
public class ResponseViewer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int fid = Integer.parseInt(request.getParameter("fid"));
        
        DbUtils db = new DbUtils();
        
        ArrayList<Response> arr = new ArrayList<>();
        
        arr = db.getAllResponses(fid);
        
        if(arr.size() != 0)
            printResponses(arr, response);
        else
            response.getWriter().print("No Responses !");
       
   
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private void printResponses(ArrayList<Response> arr, HttpServletResponse response) throws IOException {
 
        PrintWriter out = response.getWriter();
        
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
        out.println("<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>");
        out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>");
        out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>");
        
        out.println("<style type=\"text/css\">");
        out.println("table, th, td {border: 1px solid black; border-collapse: collapse; padding : 8px; text-align : left;}");
        out.println("table, h5, h3 { width : 50vw; margin-left : 25vw;}");
        out.println("h3 { margin-top: 16px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        
        String colors[] = { "CYAN", "PINK", "YELLOW"};
        
        int ci = 0;
        int rc = 1;
        
        int ii;
        for(ii=0; ii<arr.size(); ii++) {
            Response r = arr.get(ii);
            int lr = r.getLastRow();
            if(lr == 1)
                break;
        }
        
        out.println("<h3> <u>Total responses : " + arr.size()/(ii+1) + "</u></h3><br />");
        
        out.println("<h5>Response : " + rc + "</h5>");
        out.println("<table style=\"background-color :" + colors[ci] +  ";  \">");
        out.println("<tr><th>Question</th><th>Response</th></tr>");
        
        
        
        for(int i=0; i<arr.size(); i++) {
            Response r = arr.get(i);
            int lr = r.getLastRow();
            String ques = r.getQues();
            String ans = r.getAns();
            if(lr == 0) {
                out.println("<tr><td>" + ques + "</td><td>" + ans + "</td></tr>");
            } else {
                
                if( i != arr.size() - 1) {
                    out.println("</table><br />");
                    
                    ci++;
                    
                    if(ci >= colors.length)
                        ci=0;
                    
                    rc++;

                    out.println("<h5>Response : " + rc + "</h5>");
                    out.println("<table style=\"background-color :" + colors[ci] +  ";\">");
                    out.println("<tr><th>Question</th><th>Response</th></tr>");
                }
            }
        }
        
        
        out.println("</body></html>");
    }

}
