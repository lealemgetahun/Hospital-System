
package hospitalserverfinal;
import hospitalclientfinal.*;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class HospitalServerFinal  extends Application{
  private Connection con;
    private Statement st;
    private ResultSet rs;
    public HospitalServerFinal()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase","root","");
            st = con.createStatement();
        }
    
        catch(Exception e)
        { 
            System.out.println("the error is: "+e.getMessage());
        }
    }
    public static void main(String [] args){
        HospitalServerFinal ss = new HospitalServerFinal();
        Application.launch(args);
    }
// Text area for displaying contents
private TextArea ta = new TextArea();

// Number a client
private int clientNo = 0;

 // Override the start method in the Application class
  @Override
  public void start(Stage primaryStage) {
// Create a scene and place it in the stage
Scene scene = new Scene(new ScrollPane(ta), 450, 200);
primaryStage.setTitle("MultiThreadServer"); // Set the stage title
primaryStage.setScene(scene); // Place the scene in the stage
primaryStage.show(); // Display the stage
new Thread( () -> {
try {
// Create a server socket
ServerSocket serverSocket = new ServerSocket(8000);
ta.appendText("MultiThreadServer started at "
+ new Date() + '\n');

while (true) {
// Listen for a new connection request
Socket socket = serverSocket.accept();

// Increment clientNo
clientNo++;

Platform.runLater( () -> {
// Display the client number
ta.appendText("Starting thread for client " + clientNo +
" at " + new Date() + '\n');

// Find the client's host name, and IP address
InetAddress inetAddress = socket.getInetAddress();
ta.appendText("Client " + clientNo + "'s host name is "
+ inetAddress.getHostName() + "\n");
ta.appendText("Client " + clientNo + "'s IP Address is "
+ inetAddress.getHostAddress() + "\n");
});
 Thread t1 = new Thread(new HandleAClient(socket));
  t1.start();
}
}
 
 catch(IOException ex) {
 System.err.println(ex.getMessage());
 }
 }).start();
 }

 


 class HandleAClient implements Runnable {
 private Socket socket; // A connected socket
 LogIn log = new LogIn();
 Patient_history p = new Patient_history();
 /** Construct a thread */
 public HandleAClient(Socket socket) {
 this.socket = socket;
 }

 @Override
 public void run() {
 try {
 DataInputStream inputFromClient = new DataInputStream(
 socket.getInputStream());
 
 DataOutputStream outputToClient = new DataOutputStream(
 socket.getOutputStream());
 InsertData i = new InsertData();
 while (true) {
 String json = inputFromClient.readUTF();
 Gson gson = new Gson();
   
   String jsonn = json.substring(0, json.length()-4);
 
   String comand = json.substring(json.length()-4);
  if (comand.equals("   R")){
    Registor r  = gson.fromJson(jsonn, Registor.class);
    
    i.insertPatientData(r);
  }
  else if (comand.equals("  PL")){ 
      //log in patient
      logindata ll = gson.fromJson(jsonn,logindata.class);
     if(log.Patientlogin(ll)){
          ll.setB("true");  
      }else{
          ll.setB("false");
      }
      String jsson = gson.toJson(ll);
          outputToClient.writeUTF(jsson);
  }
 
  else if (comand.equals("   L")){ 
      //log in employee
      logindata ll = gson.fromJson(jsonn,logindata.class);
      if(log.login(ll)){
          ll.setB("true");  
      }else{
          ll.setB("false");
      }
      String jsson = gson.toJson(ll);
          outputToClient.writeUTF(jsson);
  }
  else if (comand.equals("   D")){ 
      // add doctor
      Doctor d = gson.fromJson(jsonn, Doctor.class);
      i.insertDoctor(d);
      
  }
  else if (comand.equals("  LB")){ 
      // add labratorist
      Labratorist_1 l = gson.fromJson(jsonn,Labratorist_1.class);
      i.insertLabData(l);
  }
  else if (comand.equals("  PH")){ 
      Parmasist p = gson.fromJson(jsonn,Parmasist.class);
      i.insertPharmaData(p);
  }
  else if (comand.equals(" DDX")){ 
      DeletFormData dd =gson.fromJson(jsonn,DeletFormData.class);
      System.out.println(jsonn);
      i.deleteDoctorData(dd);
  }
  else if (comand.equals(" DLX"))
  {
      DeletFormData dd = gson.fromJson(jsonn,DeletFormData.class);
      System.out.println(jsonn);
      i.deleteLabData(dd);
  }
  else if (comand.equals(" DPX")){ 
      
      DeletFormData dd = gson.fromJson(jsonn,DeletFormData.class);
      System.out.println(jsonn);
      i.deleteData(dd);
  }
  else if (comand.equals(" WPH")){
      //write to pharmasist
      
      ToPharmasist tp = gson.fromJson(jsonn,ToPharmasist.class);
      
      p.writeToPharmasist(tp);
  }
  else if (comand.equals(" PLR")){ 
      //write to lab
      LabData ld = gson.fromJson(jsonn, LabData.class);
      p.writeLabTest(ld);
  }
  else if(comand.equals(" ULR")){
      //udate lab result
      LabData lb = gson.fromJson(jsonn,LabData.class);
      p.updatelabresult(lb);
  }
  else if (comand.equals("  IU")){ 
      Registor r = gson.fromJson(jsonn,Registor.class);
      i.updetPatientInfo(r);
  }
  else if (comand.equals(" PPH")){ 
      //write patient history
      PatientH pH = gson.fromJson(jsonn, PatientH.class);
      p.writePatientHistory(pH);
      
  }
  else if (comand.equals(" UPH")){
      //update to pharmasisit
      ToPharmasist tp = gson.fromJson(jsonn,ToPharmasist.class);
      p.updateMedicen(tp);
  }
  else if (comand.equals(" RPH")){ 
      lab_and_med_2 la  = gson.fromJson(jsonn, lab_and_med_2.class);
      p.readpatientHistory(la);
      
      String jsons = gson.toJson(p.ph); 
      
     
      outputToClient.writeUTF(jsons);
      outputToClient.flush();
  }
  else if (comand.equals(" RLR")){ 
      //read lab result
     lab_and_med_2 la  = gson.fromJson(jsonn, lab_and_med_2.class);
     p.readpatientLab(la);
     
      String jsons = gson.toJson(p.ld); 
      
     
      outputToClient.writeUTF(jsons);
      outputToClient.flush();
  }
  else if (comand.equals(" RPH")){ 
      //read pharmasist
      lab_and_med_2 la  = gson.fromJson(jsonn, lab_and_med_2.class);
      p.readpatientMedicen(la);
      
      String jsons = gson.toJson(p.tp); 
      
     
      outputToClient.writeUTF(jsons);
      outputToClient.flush();
      
  }
  else{ 
      System.err.println("enter appropirate in put");
  }
 }
 }
 catch(IOException e) {
     System.out.println(e.getMessage());
 }
 }
 }
}
