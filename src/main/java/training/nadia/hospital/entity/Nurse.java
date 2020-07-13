package training.nadia.hospital.entity;

import java.util.HashMap;
import java.util.Map;

public class Nurse extends User implements MedicalStaff {

    private StaffType staffType = StaffType.NURSE;

//    private List<Patient> patients;

    private Map<Patient, Integer> patientProcedure;

    public Nurse() {
        patientProcedure = new HashMap<>();
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

    public Map<Patient, Integer> getPatientProcedure() {
        return patientProcedure;
    }

    public void setPatientProcedure(Map<Patient, Integer> patientProcedure) {
        this.patientProcedure = patientProcedure;
    }

    public void setPatientProcedure(Patient patient, Integer numberOfProcedure) {
        patientProcedure.put(patient, numberOfProcedure);
    }
}
