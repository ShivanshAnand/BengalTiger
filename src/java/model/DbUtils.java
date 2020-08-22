/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pojo.Form;
import pojo.Response;
import pojo.User;

/**
 *
 * @author hp
 */
public class DbUtils {
    
    private ConnectionManager cm;
    
    public DbUtils() {
        cm = new ConnectionManager();
    }
    
    public User getUser(String email, String password) {
        User u = null;
        
        Connection conn = cm.getConnection();
        
        if( conn != null) {
            try {
                String q = "SELECT * FROM user WHERE " +
                            " email = " + "\"" + email + "\"" +
                            " AND password = " + "\"" + password + "\"";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(q);
                
                if(rs.next()) {
                    int uid = rs.getInt("uid");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email1 = rs.getString("email");
                    String pass = rs.getString("password");
                    u = new User(uid, firstName, lastName, email1, password);
                }
                            
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                if(conn != null) {
                    try {
                        conn.close(); }
                    catch(SQLException e) {
                        System.out.println(e.getMessage()); }
                            }
            }
        }
        
        return u;
    }
    
        public ArrayList<Form> getForm(int uid) {
        
        ArrayList<Form> arr = new ArrayList<>();
        
        Connection conn = cm.getConnection();
        
        if( conn != null) {
            try {
                String q = "SELECT * FROM form_info WHERE " +
                            " uid = " + uid;
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(q);
                
                while(rs.next()) {
                    int uid1 = rs.getInt("uid");
                    int fid = rs.getInt("fid");
                    String title = rs.getString("title");
                    Form f = new Form(uid1, fid, title);
                    arr.add(f);
                }
                            
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                if(conn != null) {
                    try {
                        conn.close(); }
                    catch(SQLException e) {
                        System.out.println(e.getMessage()); }
                            }
            }
        }
        
        return arr;       
    }
    
    
    public boolean createNewForm(int uid, String title) {
        
        Connection conn = cm.getConnection();
        
        boolean s = true;
        
        if( conn != null) {
            try {
                String q = "INSERT INTO form_info (uid, title) VALUES(" +
                            uid + "," + "\"" + title + "\"" + ")";
                
                Statement st = conn.createStatement();
                int n = st.executeUpdate(q);
                            
            } catch(SQLException e) {
                s = false;
                System.out.println(e.getMessage());
            } finally {
                if(conn != null) {
                    try {
                        conn.close(); }
                    catch(SQLException e) {
                        System.out.println(e.getMessage()); }
                            }
            }
        } 
        
        return s;
        
    }
    
    public boolean saveForm(int fid, ArrayList<String> ques, ArrayList<String> ans) {
        Connection conn = cm.getConnection();
        
        boolean s = true;
        
        if( conn != null) {
            try {
                
                String q = "SET FOREIGN_KEY_CHECKS = 0";
                Statement st = conn.createStatement();
                st.execute(q);
                
                q = "DELETE FROM blocks WHERE fid = " + fid;
                st.execute(q);
                
                q = "DELETE FROM questions WHERE fid = " + fid;
                st.execute(q);
                
                q = "DELETE FROM placeholders WHERE fid = " + fid;
                st.execute(q);
                
                q = "SET FOREIGN_KEY_CHECKS = 1";
                st.execute(q);
                
                q = "INSERT INTO blocks ( fid, block_order ) " + "VALUES ";
                
                for(int i=0; i<ques.size(); i++) {
                    q = q + "(" + fid + "," + i + ")";  
                    if(i != ques.size() - 1)
                        q = q + ",";
                }
                
                System.out.println(q);
                
                st.executeUpdate(q);
                
                q = "SELECT bid FROM blocks WHERE fid = " + fid;
                ResultSet rs = st.executeQuery(q);
                
                ArrayList<Integer> bids = new ArrayList<>();
                
                while(rs.next())
                    bids.add(rs.getInt("bid"));
                
                if(bids.size() == ques.size())
                    System.out.println("Yes !");
                else
                    System.out.println("No !");
                
                
                q = "INSERT INTO questions (fid, bid, ques) VALUES";
                
                for(int i=0; i<ques.size(); i++) {
                    String q1 = ques.get(i);
                    q1 = q1.replaceAll("'", "''");
                    q = q + "(" + fid + "," + bids.get(i) + ",\"" + q1 + "\")";
                    if(i != ques.size() - 1)
                        q = q + ",";
                }
                
                System.out.println(q);
                st.executeUpdate(q);
                
                q = "INSERT INTO placeholders ( fid, bid,value,  tool_type ) VALUES";
                
                for(int i=0; i<ans.size(); i++) {
                    q = q + "(" + fid + "," + bids.get(i) + "," + "\"empty\"" + ",";
                    if(ans.get(i).equals("name")) {
                        q = q + 1 + ")";
                    } else if(ans.get(i).equals("slt")) {
                        q = q + 2 + ")";
                    } else if(ans.get(i).equals("mlt")) {
                        q = q + 3 + ")";
                    } else if(ans.get(i).equals("s")) {
                        q = q + 5 + ")";
                    }
                    if(i != ans.size() - 1)
                        q = q + ",";
                }
                
                st.executeUpdate(q);
                
                System.out.println(q);
                            
            } catch(SQLException e) {
                s = false;
                System.out.println(e.getMessage());
            } finally {
                if(conn != null) {
                    try {
                        conn.close(); }
                    catch(SQLException e) {
                        System.out.println(e.getMessage()); }
                            }
            }
        } 
        
        return s;
    }
    
    public ArrayList<String> getRenderableForm(int fid) {
        
        Connection conn = cm.getConnection();
        
        if( conn != null) {
            try {
                
                ArrayList<Integer> arr = new ArrayList<>();
                ArrayList<String> ques = new ArrayList<>();
                ArrayList<String> ans = new ArrayList<>();
                Statement st;
                
                String q = "SELECT * FROM blocks WHERE fid = " + fid + " ORDER BY block_order";
                
                st = conn.createStatement();
                ResultSet rs = st.executeQuery(q);
                
                while(rs.next())
                    arr.add(rs.getInt("bid"));
                
                for(Integer i : arr) {
                    q = "SELECT ques FROM questions WHERE bid = " + i.intValue() + " AND fid = " + fid;
                    rs = st.executeQuery(q);
                    rs.next();
                    ques.add("<input type=\"text\" name=\"ques\" readonly style=\"font-size : 24px; outline : none; border:none; color : black; font-weight:bold;\" value=\"" + rs.getString("ques") + "\"</input></br>");
                }
                
                for(Integer i : arr) {
                    q = "SELECT tool_type FROM placeholders WHERE bid = " + i.intValue() + " AND fid = " + fid;
                    rs = st.executeQuery(q);
                    rs.next();
                    int t = rs.getInt("tool_type");
                    String add = "";
                    if(t == 1) {
                        add = "<input type=\"text\" name=\"resp\" class=\"full-inp\"/>";
                    } else if(t == 2) {
                        add = "<input type=\"text\" name=\"resp\" class=\"full-inp\"/>";
                    } else if(t == 3 ) {
                        add = "<textarea name=\"resp\" class=\"full-inp\"></textarea>";
                    } else if(t == 5) {
                        add = "<input type=\"range\" name=\"resp\" class=\"full-inp\" />";
                    }
                    ans.add(add);
                }
                
                ArrayList<String> farr = new ArrayList<>();
                
                for(int i=0; i< ques.size(); i++) {
                    farr.add(ques.get(i));
                    farr.add(ans.get(i));
                }
                
                System.out.println("farr");
                
                return farr;
                
                
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                if(conn != null) {
                    try {
                        conn.close(); }
                    catch(SQLException e) {
                        System.out.println(e.getMessage()); }
                            }
            }
        } 
        
        return new ArrayList<>();
               
    }
    
    public boolean saveResponse(int fid, String ques[], String ans[]) {
        
        Connection conn = cm.getConnection();
        boolean s = true;
                
        if( conn != null) {
            try {
                
                Statement st = conn.createStatement();
                String q = "";
                
                for(int i=0; i<ques.length; i++) {
                    q = "INSERT INTO responses (fid, question, answer, last_row) VALUES (" +
                                fid + "," +
                                "\"" + ques[i] + "\"" + "," +
                                "\"" + ans[i] + "\"" + "," + 
                                0 + ")";
                    
                    st.executeUpdate(q);
                }
                
                q = "INSERT INTO responses (fid, last_row) VALUES (" + fid + "," + 1 + ")"; 
                                
                conn.createStatement();
                st.executeUpdate(q);
                            
            } catch(SQLException e) {
                s = false;
                System.out.println(e.getMessage());
            } finally {
                if(conn != null) {
                    try {
                        conn.close(); }
                    catch(SQLException e) {
                        System.out.println(e.getMessage()); }
                            }
            }
        } 
        
        return s;
        
    }
    
    public ArrayList<Response> getAllResponses(int fid) {
        
        Connection conn = cm.getConnection();
        
        ArrayList<Response> respArr = new ArrayList<>();
                
        if( conn != null) {
            try {
                
                Statement st = conn.createStatement();
                String q = "SELECT question, answer, last_row FROM responses WHERE fid = " + fid;
                
                ResultSet rs = st.executeQuery(q);
                
                while(rs.next()) {
                    String ques = rs.getString("question");
                    String ans = rs.getString("answer");
                    int lastRow = rs.getInt("last_row");
                    Response res = new Response(ques, ans, lastRow);
                    respArr.add(res);
                }
                            
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                if(conn != null) {
                    try {
                        conn.close(); }
                    catch(SQLException e) {
                        System.out.println(e.getMessage()); }
                            }
            }
        } 
        
        return respArr;
        
    }
    
}
