/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Database {

    private String url = "jdbc:mysql://localhost:3306/studentapp";
    private String username = "root";
    private String password = "";
    public Connection conn;
    public static Database dbb;
    
    private Database() throws SQLException{
        connect();
    }

    public boolean connect() throws SQLException {

        conn = DriverManager.getConnection(url, username, password);
        return true;
    }

    public static Database getInstance() throws SQLException {
        if (dbb == null) {
            dbb = new Database();
        }
        return dbb;
    }

    public Connection getConnection() {
        return conn;

    }

}
