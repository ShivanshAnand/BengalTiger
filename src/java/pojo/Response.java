/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author hp
 */
public class Response {
    
    private String ques;
    private String ans;
    private int lastRow;

    public Response() {
    }

    public Response(String ques, String ans, int lastRow) {
        this.ques = ques;
        this.ans = ans;
        this.lastRow = lastRow;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public int getLastRow() {
        return lastRow;
    }

    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }
    
    
    
}
