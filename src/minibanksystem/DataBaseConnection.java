
package minibanksystem;

import java.sql.*;

public class DataBaseConnection {
    Connection conn;
    Statement stateMen;
    
    public DataBaseConnection(){
        try{
        //Class.forName("com.mysql.cj.jdbc.Driver");
        
        conn = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "root");
        stateMen = conn.createStatement();
        }catch(Exception e){
            System.out.println("Error:" + e);
        }
    }
}
