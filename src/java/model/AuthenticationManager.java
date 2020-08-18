package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthenticationManager {
    
    private ConnectionManager cm;
    
    public AuthenticationManager() {
        cm = new ConnectionManager();
    }
    
    public void putUser(String firstName, String lastName, String email, String password) {
        
        Connection conn = cm.getConnection();
        
        if(conn != null) {
            String q = "INSERT INTO user(first_name, last_name, email, password) VALUES ("
                        + "\"" + firstName + "\","
                        + "\"" + lastName + "\","
                        + "\"" + email + "\","
                        + "\"" + password + "\"" + ")";
            try {
                Statement st = conn.createStatement();
                st.executeUpdate(q);
            } catch(SQLException e) {
                System.out.println(e.getMessage());
              
            } finally {
                try {
                    conn.close();        
                } catch(SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    
    public boolean doesUserExists(String email, String password) {
        
        Connection conn = cm.getConnection();
        
        if(conn != null) {
            String q = "SELECT uid, first_name, last_name, email FROM user WHERE " +
                        "email=" + "\"" + email + "\"" + 
                        " AND password=" + "\"" + password + "\"";
            System.err.println(q);
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(q);
                return rs.next();
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    conn.close();
                } catch(SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        
        return false;
    }
    
}
