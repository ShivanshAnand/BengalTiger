/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class ConnectionManager {
    
    private Connection conn = null;
    
    public Connection getConnection() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/form_db";
            String username = "root";
            String password = "mypass_mysql_1";
            conn = DriverManager.getConnection(url, username, password);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        return conn;
        
    }
    
}
