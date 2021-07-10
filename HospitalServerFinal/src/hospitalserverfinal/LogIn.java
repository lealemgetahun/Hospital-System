
package hospitalserverfinal;

import hospitalclientfinal.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JTextField;


public class LogIn {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    DatabaseConnection bd;
    public LogIn(){ //call the singlton connection
         bd = DatabaseConnection.getDataBaseConnection();
        bd.connection();
    }
    Scanner input = new Scanner(System.in);
    public boolean Patientlogin(logindata l){
        
        int i =0;
        try {
            String query = "select * from javadatabase.patient";
            PreparedStatement pts = bd.con.prepareStatement(query);
            rs = pts.executeQuery();
            while(rs.next()){
                String name = rs.getString("F_name");
                String password = rs.getString("password");
                if( name.equals(l.getUsername())  && password.equals(l.getPassword())){ 
                   return true;
                }
               
                 
            }
        } catch (Exception ex) {
            System.err.println("error");
        }
        return false;
        
        
    }
    public boolean login(logindata l){
        
        int i =0;
        try {
            String query1 = "select * from javadatabase.login";
            PreparedStatement pts = bd.con.prepareStatement(query1);
            rs = pts.executeQuery();
               while(rs.next()){
                String name = rs.getString("UserName");
                String password = rs.getString("password");
                if( name.equals(l.getUsername())  && password.equals(l.getPassword())){ 
                   return true;
                }
               
                 
            }
        } catch (Exception ex) {
            System.err.println("error");
        }
        return false;
          
     }
   
   
}

