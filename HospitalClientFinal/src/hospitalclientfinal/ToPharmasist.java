
package hospitalclientfinal;

public class ToPharmasist {
    String patientId ;
        String doctorId ;
        String pharmaId ;
        String medicineName ;
        String name ;
        String amount;

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPharmaId() {
        return pharmaId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getName() {
        return name;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setPharmaId(String pharmaId) {
        this.pharmaId = pharmaId;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setName(String name) {
        this.name = name;
    }
        
}
