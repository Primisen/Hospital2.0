package training.nadia.hospital.entity;

import java.util.HashMap;
import java.util.Map;

public class Nurse extends User implements MedicalStaff {

    private StaffType staffType = StaffType.NURSE;

//    private List<Patient> patients;

    private Map<Patient, Integer> patientTherapies;

    public Nurse() {
        patientTherapies = new HashMap<>();
    }

    @Override
    public StaffType getStaffType() {
        return staffType;
    }

//    public List<Patient> getPatients() {
//        return patients;
//    }

//    public void setPatients(List<Patient> patients) {
//        this.patients = patients;
//    }

    public Map<Patient, Integer> getPatientTherapies() {
        return patientTherapies;
    }

    public void setPatientTherapies(Map<Patient, Integer> patientProcedure) {
        this.patientTherapies = patientProcedure;
    }

    public void setPatientTherapies(Patient patient, Integer numberOfTherapies) {
        patientTherapies.put(patient, numberOfTherapies);
    }
}
