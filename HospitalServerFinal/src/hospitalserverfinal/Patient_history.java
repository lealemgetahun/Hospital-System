
package hospitalserverfinal;

import hospitalclientfinal.LabData;
import hospitalclientfinal.PatientH;
import hospitalclientfinal.ToPharmasist;
import hospitalclientfinal.lab_and_med_2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Patient_history {
      private Connection con;
    private Statement st;
    private ResultSet rs;
    DatabaseConnection bd;
    public Patient_history(){ 
        
        bd = DatabaseConnection.getDataBaseConnection();
        bd.connection();
    }
   

    void writePatientHistory(PatientH p) {
         try{ 
            PreparedStatement pts = bd.con.prepareStatement(" INSERT INTO patientmedicalhistory VALUES (?,?,?,?,?,?)");
            PreparedStatement pts1 = bd.con.prepareStatement("UPDATE patientmedicalhistory SET Bloodpressure = ?, HeartBeat = ?,result = ? WHERE PatientId = ? ");
            String i = null;
            String id = null ;
            String query = "select * from javadatabase.patientmedicalhistory ";
            PreparedStatement pts2 = bd.con.prepareStatement(query);
            
        rs = pts2.executeQuery();
            while(rs.next()){
                id = rs.getString("PatientId");
                if (id != p.getPatientId())
                    i = "insert";
            }
            if ( i.equals("insert")){ 
                pts.setString(1, p.getPatientId());
                pts.setString(3, p.getBloodPressure());
                pts.setString(4, p.getHeartBeat());
                pts.setString(5, p.getName());
                pts.setString(2, p.getDoctorId());
                pts.setString(6, p.getResult());
                pts.executeUpdate();
            }
            
            else{ 
                pts1.setString(1,p.getBloodPressure());
                pts1.setString(2, p.getHeartBeat());
                pts1.setString(3, p.getResult());
                pts1.setString(4, p.getPatientId());
                pts1.executeUpdate();
            }
      
        }
        catch(Exception e){ 
            System.err.println(e.getMessage());
        }
    }
    PatientH ph = new PatientH();
    void readpatientHistory(lab_and_med_2 la){ 
        
        try{
            String query = "select * from javadatabase.patientmedicalhistory where PatientID = ?";
            PreparedStatement pts = bd.con. prepareStatement(query);
            pts.setString(1,la.getId());
        rs = pts.executeQuery();
       
        while(rs.next()){ 
            ph.setName(rs.getString("name"));
            ph.setPatientId(rs.getString("PatientID"));
            ph.setDoctorId(rs.getString("doctorID"));
            ph.setBloodPressure(rs.getString("bloodpressure"));
            ph.setHeartBeat( rs.getString("HeartBeat"));
            ph.setResult( rs.getString("result"));
          
        }
        }
        catch(Exception e){ 
            System.out.println("error");
        }
        
    }
    void writeToPharmasist(ToPharmasist tp){ 
        try{
                   int i = 0;
            String id = null ;
            String query = "select * from javadatabase.todoctortopharma ";
            PreparedStatement pts2 = bd.con.prepareStatement(query);
            
        rs = pts2.executeQuery();
            while(rs.next()){
                id = rs.getString("PatientId");
                if (id != tp.getPatientId())
                    i = 1;
            }
            if(i == 1){
                PreparedStatement pts = bd.con.prepareStatement("INSERT INTO javadatabase.todoctortopharma VALUES (?,?,?,?,?,?)");
                pts.setString(1, tp.getPatientId());
                pts.setString(2, tp.getDoctorId());
                pts.setString(3, tp.getPharmaId());
                pts.setString(4, tp.getMedicineName());
                pts.setString(5, tp.getName());
                pts.setString(6, tp.getAmount());
                pts.executeUpdate();
            }
            else{ 
                PreparedStatement pts1 = bd.con.prepareStatement("UPDATE todoctortopharma SET medicenName = ?, name = ? WHERE = ?");
                pts1.setString(1, tp.getMedicineName());
                pts1.setString(2, tp.getName());
                pts1.setString(3, tp.getPatientId());
                pts1.executeUpdate();
         
            }
          
        }
        catch(Exception e){ 
            System.err.println(e.getMessage());
        }
    }
    ToPharmasist tp = new ToPharmasist();
    void readpatientMedicen(lab_and_med_2 la){ 
        try{
            String query = "select * from javadatabase.todoctortopharma where PatientID = ?";
            PreparedStatement pts = bd.con.prepareStatement(query);
            pts.setString(1, la.getId());
        rs = pts.executeQuery();
        
        while(rs.next()){ 
            tp.setName(rs.getString("name"));
            tp.setPatientId(rs.getString("PatientID"));
            tp.setDoctorId(rs.getString("doctorID"));
            tp.setPharmaId( rs.getString("pharmaID"));
            tp.setMedicineName(rs.getString("medicenName")); 
            
        }
        }
        catch(Exception e){ 
            System.out.println("error");
        }
    }
    void writeLabTest(LabData lb){ 
        try{
                          int i = 0;
            String id = null ;
            String query = "select * from javadatabase.todoctortolab ";
            PreparedStatement pts2 = bd.con.prepareStatement(query);
            
        rs = pts2.executeQuery();
            while(rs.next()){
                id = rs.getString("PatientId");
                if (id != lb.getPatientId())
                    i = 1;
            }
            if(i == 1){
            PreparedStatement pts = bd.con.prepareStatement("INSERT INTO todoctortolab VALUES (?,?,?,?,?,?)");
            pts.setString(1, lb.getPatientId());
            pts.setString(2, lb.getDoctorId());
            pts.setString(3, lb.getLabId());
            pts.setString(4, lb.getTestType());
            pts.setString(5, lb.getName());
            pts.setString(6, lb.getResult());
            
            pts.executeUpdate();}
            else{ 
                PreparedStatement pts1 = bd.con.prepareStatement("UPDATE todoctortolab SET result = ?, TestType = ? WHERE PatientId = ?" );
                pts1.setString(2, lb.getTestType());
                pts1.setString(1, lb.getResult());
                pts1.setString(3, lb.getPatientId());
                
                pts1.executeUpdate();
            }
        }
        catch(Exception e){ 
            System.err.println(e.getMessage());
        }
    }
    LabData ld = new LabData();
       void readpatientLab(lab_and_med_2 la){ 
        try{
            String query = "select * from javadatabase.todoctortolab where PatientID = ?";
            PreparedStatement pts = bd.con.prepareStatement(query);
            pts.setString(1, la.getId());
        rs = pts.executeQuery();
        while(rs.next()){ 
            ld.setName(rs.getString("name"));
            ld.setPatientId(rs.getString("PatientID"));
            ld.setDoctorId(rs.getString("doctorID"));
            ld.setLabId(rs.getString("lab_id"));
            ld.setTestType(rs.getString("TestType")); 
            ld.setResult(rs.getString("result"));
            }
        }
        catch(Exception e){ 
            System.out.println("error");
        }
    }
    void updatelabresult(LabData lb){ 
        try {
            PreparedStatement pts = bd.con.prepareStatement("update todoctortolab set result = ?  where PatientID = ?");
            
                pts.setString(1, lb.getResult());
                pts.setString(2, lb.getPatientId());
            
            
            pts.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     void updateMedicen(ToPharmasist tp){ 
        try {
            PreparedStatement pts = bd.con.prepareStatement("update todoctortopharma set Amount = ?  where PatientID = ?");
            
                pts.setString(1, tp.getAmount());
                pts.setString(2, tp.getPatientId());
            
            
            pts.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

