
package hospitalclientfinal;


public class PatientH {
    String patientId ;
        String doctorId ;
        String BloodPressure ;
        String heartBeat;
        String name ;
        String result ;

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getBloodPressure() {
        return BloodPressure;
    }

    public String getHeartBeat() {
        return heartBeat;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setBloodPressure(String BloodPressure) {
        this.BloodPressure = BloodPressure;
    }

    public void setHeartBeat(String heartBeat) {
        this.heartBeat = heartBeat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResult(String result) {
        this.result = result;
    }
        
}
