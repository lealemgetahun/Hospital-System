
package hospitalserverfinal;

import hospitalclientfinal.*;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.logging.Logger;

import javax.swing.JTextField;
public class InsertData {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    DatabaseConnection bd ;
    public InsertData()
    {
         bd = DatabaseConnection.getDataBaseConnection();
        bd.connection();
        
        
    }
    
    public void insertPatientData(Registor r){
        
        try{
            
     PreparedStatement pts = bd.con.prepareStatement("INSERT INTO javadatabase.patient VALUES (?,?,?,?,?,?,?)");
            pts.setString(1, r.getP_id());
            pts.setString(2, r.getFname());
            pts.setString(3, r.getLname());
            pts.setString(4, r.getSex());
            pts.setString(5, r.getAge());
            pts.setString(6, r.getAddress());
            
            pts.setString(7, r.getPassword());
            
            
            int i =pts.executeUpdate();
            System.out.println(i + "recored inserted");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            
        }
        
      
    }
       public void insertLabData(Labratorist_1 l){
        
       String q2 = "INSERT INTO login VALUES (?,?,?)";
        try {
            PreparedStatement pts1 = bd.con.prepareStatement("INSERT INTO labratorist VALUES (?,?,?,?,?)");
            PreparedStatement pts2 = bd.con.prepareStatement(q2);
            pts1.setString(3, l.getLanId());
            pts1.setString(1, l.getFname());
            pts1.setString(2, l.getLname());
            pts1.setString(4, l.getUsername());
            pts1.setString(5, l.getPassword());
            pts2.setString(1, l.getLanId());
            pts2.setString(2,l.getUsername());
            pts2.setString(3,l.getPassword());
            int i =pts1.executeUpdate();
            pts2.executeUpdate();
            
            System.out.println(i + "recored inserted");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
      
    }
         
    public void insertDoctor(Doctor d){ 
        String q2 = "INSERT INTO login VALUES (?,?,?)";
        String q1 = "INSERT INTO doctor VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pts1 = bd.con.prepareStatement(q1);
            PreparedStatement pts2 = bd.con.prepareStatement(q2);
            pts1.setString(1, d.getD_id());
            pts1.setString(2, d.getMname());
            pts1.setString(3, d.getLname());
            pts1.setString(4,d.getGender());
            
            pts1.setString(5, d.getAddress());
            pts1.setString(6, d.getSpecialization());
            pts1.setString(7, d.getUsername());
            pts1.setString(8, d.getPassword());
            pts2.setString(1, d.getD_id());
            pts2.setString(2,d.getUsername());
            pts2.setString(3,d.getPassword());
            int i =pts1.executeUpdate();
            pts2.executeUpdate();
            
            System.out.println(i + "recored inserted");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
       public void insertPharmaData(Parmasist p){
        String q2 = "INSERT INTO login VALUES (?,?,?)";
       
        try {
            PreparedStatement pts1 = bd.con.prepareStatement("INSERT INTO pharmasist VALUES (?,?,?,?,?)");
            PreparedStatement pts2 = bd.con.prepareStatement(q2);
            pts1.setString(3, p.getPharmaId());
            pts1.setString(1, p.getFname());
            pts1.setString(2, p.getLname());
            pts1.setString(4, p.getUsername());
            pts1.setString(5, p.getPassword());
            pts2.setString(1, p.getPharmaId());
            pts2.setString(2,p.getUsername());
            pts2.setString(3,p.getPassword());
            int i =pts1.executeUpdate();
            pts2.executeUpdate();
            
            System.out.println(i + "recored inserted");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
      
    }
         public void deleteData(DeletFormData dd ){ 
        String query;
        query = "delete from javadatabase.pharmasist where PharmaID = ?";
             System.out.println(dd.getId());
        try {
            System.out.println(dd.getId());
            PreparedStatement pts = bd.con.prepareStatement(query);
            pts.setString(1,dd.getId() );
            int i = pts.executeUpdate();
            System.out.println(i +" record delated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    /*public void deletePatientData(int value ){ 
        String query;
        query = "delete from javadatabase.patient where p_ID = ?";
        try {
            PreparedStatement pts = con.prepareStatement(query);
            pts.setInt(1, value);
            pts.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
        
    }*/
    public void updetPatientInfo(Registor r){ 
        
        try {
            PreparedStatement pts = bd.con.prepareStatement("update patient set Addres = ?,password=?  where p_ID = ?");
            pts.setString(3, r.getP_id());
            pts.setString(1, r.getAddress());
            pts.setString(2, r.getPassword());
            
            pts.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void deleteDoctorData(DeletFormData d){ 
        String query;
        query = "delete from javadatabase.doctor where Doctor_id = ?";
        try {
            PreparedStatement pts = bd.con.prepareStatement(query);
            pts.setString(1,d.getId() );
            int i = pts.executeUpdate();
            System.out.println(i +" record delated");
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
        
    }
              
        public void deleteLabData(DeletFormData d){
        String query = "delete from javadatabase.labratorist where LabID = ?";
            System.out.println(d.getId());
        try {
            PreparedStatement pts = bd.con.prepareStatement(query);
            pts.setString(1, d.getId());
            int i = pts.executeUpdate();
            System.out.println(i +" record delated");
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
        
    }
      
    /*
    public void getPatientData(){ 
        try{
        String query = "select * from javadatabase.patient";
        rs = st.executeQuery(query);
        System.out.println("recordes from table");
        System.out.println("name"+"               "+"Id"+ "    "+"Age" + "   "+"sex"+ "      ");
        while(rs.next()){ 
            String name = rs.getString("F_name");
            int ID = rs.getInt("p_ID");
            int Age = rs.getInt("Age");
            String sex = rs.getString("Sex");
            System.out.println(name + "             "+ID +"    "+ Age +"   "+sex + "     ");
        }
        }
        catch(Exception e){ 
            System.out.println("error");
        }
            
            
            
            
    }
  
        public void getDoctorstData(){ 
        try{
        String query = "select * from javadatabase.doctor";
        rs = st.executeQuery(query);
        System.out.println("recordes from table");
        System.out.println("name"+"               "+"Id"+ "    "+"Specialization" + "   "+"sex"+ "      ");
        while(rs.next()){ 
            String name = rs.getString("M_name");
            int ID = rs.getInt("Doctor_id");
            String specialization = rs.getString("specialization");
            String sex = rs.getString("Gender");
            System.out.println(name + "             "+ID +"    "+ specialization +"   "+sex + "     ");
        }
        }
        catch(Exception e){ 
            System.out.println("error");
        }
            
            
            
            
    }
            public void deleteDoctorData(int value ){ 
        String query;
        query = "delete from javadatabase.doctor where Doctor_id = ?";
        try {
            PreparedStatement pts = con.prepareStatement(query);
            pts.setInt(1, value);
            pts.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
        
    }
    /*public void updetDoctorInfo(String username, int ID , String password){ 
        
        try {
            PreparedStatement pts = con.prepareStatement("update Doctor set username = ? , password=?  where Doctor_id = ?");
            pts.setInt(3, ID);
            pts.setString(1, username);
            pts.setString(2, password);
            
            pts.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
 
 
    /*public void updetPharmaInfo(String username, int ID, String password){ 
        
        try {
            PreparedStatement pts = con.prepareStatement("update pharmasist set username = ?,password=?  where PharmaID = ?");
            pts.setInt(3, ID);
            pts.setString(1, username);
            pts.setString(2, password);
            
            pts.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    public void getPharmaData(){ 
        try{
        String query = "select * from javadatabase.pharmasist";
        rs = st.executeQuery(query);
        System.out.println("recordes from table");
        System.out.println("name"+"               "+"Id"+ "    ");
        while(rs.next()){ 
            String name = rs.getString("FName");
            int ID = rs.getInt("PharmaID");
            String Lname = rs.getString("LName");
            System.out.println(name +" "+ Lname+"                "+ID +"    ");
        }
        }
        catch(Exception e){ 
            System.out.println("error");
        }
            
            
            
            
    }
 

   /*public void updetLabInfo(String username, int ID, String password){ 
        
        try {
            PreparedStatement pts = con.prepareStatement("update labratorist set username = ?,password=?  where LabID = ?");
            pts.setInt(3, ID);
            pts.setString(1, username);
            pts.setString(2, password);
            
            pts.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    public void getLabData(){ 
        try{
        String query = "select * from javadatabase.labratorist";
        rs = st.executeQuery(query);
        System.out.println("recordes from table");
        System.out.println("name"+"         "+"Id"+ "    ");
        while(rs.next()){ 
            String name = rs.getString("FName");
            int ID = rs.getInt("LabID");
            String Lname = rs.getString("LName");
            System.out.println(name +" "+ Lname+"          "+ID +"    ");
        }
        }
        catch(Exception e){ 
            System.out.println("error");
        }
            
            
            
            
    } */   
}


    

