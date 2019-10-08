/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class DBHandler extends Configs {
    Connection dbconnection;
    
    public Connection getConnection() throws ClassNotFoundException, SQLException
    {
        String ConnectionString = "jdbc:mysql://" + dbhost + ":" + dbport + "/" + dbname + "?autoReconnect=true&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        
        dbconnection = DriverManager.getConnection(ConnectionString,dbuser,dbpass);
        
        return dbconnection;
    }
}
