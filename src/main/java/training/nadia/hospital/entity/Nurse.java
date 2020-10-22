package training.nadia.hospital.entity;

import java.util.HashSet;
import java.util.Set;

public class Nurse extends User {

    private Set<Patient> patient;

    public Nurse() {
        patient = new HashSet<>();
        setRole(Role.NURSE);
    }

    public Set<Patient> getPatient() {
        return patient;
    }

    public void setPatient(Set<Patient> patient) {
        this.patient = patient;
    }

    public void addPatient(Patient patient) {
        this.patient.add(patient);
    }
}
