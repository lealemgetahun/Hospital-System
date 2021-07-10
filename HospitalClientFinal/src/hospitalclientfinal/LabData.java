
package hospitalclientfinal;

public class LabData {
    String patientId ;
        String doctorId ;
        String labId;
        String testType ;
        String name ;
        String result;

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getLabId() {
        return labId;
    }

    public String getTestType() {
        return testType;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }
        
}
