/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/response-saver"})
public class ResponseSaver extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String qs[] = request.getParameterValues("ques");
        String re[] = request.getParameterValues("resp");
        int fid = Integer.parseInt(request.getParameter("fid"));
        
        PrintWriter out = response.getWriter();
       
        
        if(qs.length == re.length) {
            DbUtils db = new DbUtils();
            boolean s = db.saveResponse(fid, qs, re);
            
            if(s)
                out.println("Response recorded !");
            else
                out.println("Some error occurred !");
        }
        
    }

}
