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

/**
 *
 * @author hp
 */
@WebServlet(urlPatterns = {"/form-handler"})
public class FormSaver extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uid = request.getParameter("uid");
        String fid = request.getParameter("fid");
        
        String blocks[] = request.getParameterValues("ex");
        
        PrintWriter out = response.getWriter();
        
        ArrayList<String> ques = new ArrayList<>();
        ArrayList<String> answerType = new ArrayList<>();
        
        for(int i=0; i<blocks.length; i++) {
            if(blocks[i].indexOf("-") != -1) {
                String q = blocks[i].substring(0, blocks[i].indexOf("-"));
                String at = blocks[i].substring(blocks[i].indexOf("-") + 1);               
                ques.add(q);
                answerType.add(at);
            }
        }
        
        if(ques.size() == answerType.size()) {
            
            boolean s = new DbUtils().saveForm(Integer.parseInt(fid), ques, answerType);
            if(s)
                out.println("yes");
            else
                out.println("db error");
            
        } else {
            out.println("no");
        }
        
    }
    


}
