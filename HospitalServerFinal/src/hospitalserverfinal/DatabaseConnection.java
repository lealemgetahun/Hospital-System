
package hospitalserverfinal;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static DatabaseConnection db;
    Connection con;
    DatabaseConnection(){}
    public Connection connection(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase","root","");
        if(con != null){
            System.out.println("conneciton established.");
        }
        
        }catch(Exception ex){
            ex.getMessage();
        }
        return con;
    }
    public static DatabaseConnection getDataBaseConnection(){
        if(db == null){
            db = new DatabaseConnection();
        }
        return db;
    }
    public static void main(String [] args){
        DatabaseConnection bd = DatabaseConnection.getDataBaseConnection();
        bd.connection();
    }
}
