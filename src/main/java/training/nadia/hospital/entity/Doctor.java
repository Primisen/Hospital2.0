package training.nadia.hospital.entity;

import java.util.*;

public class Doctor extends User {

    private Set<Patient> patientsToCure;
    private Set<Patient> patientsToReceive;

    public Doctor() {

        patientsToCure = new HashSet<>();
        patientsToReceive = new HashSet<>();
        setRole(Role.DOCTOR);
    }

    public Doctor(String name, String surname) {
        super(name, surname);
    }

    public Set<Patient> getPatientsToCure() {
        return patientsToCure;
    }

    public void setPatientsToCure(Set<Patient> patientsToCure) {
        this.patientsToCure = patientsToCure;
    }

    public void addPatientToCure(Patient patient) {
        patientsToCure.add(patient);
    }

    public Set<Patient> getPatientsToReceive() {
        return patientsToReceive;
    }

    public void setPatientsToReceive(Set<Patient> patientsToReceive) {
        this.patientsToReceive = patientsToReceive;
    }

    public void addPatientToReceive(Patient patient) {
        patientsToReceive.add(patient);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Doctor doctor = (Doctor) object;

        return Objects.equals(patientsToCure, doctor.patientsToCure) &&
                Objects.equals(patientsToReceive, doctor.patientsToReceive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientsToCure, patientsToReceive);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Doctor{");
        sb.append("patientsToCure=").append(patientsToCure);
        sb.append(", patientsToReceive=").append(patientsToReceive);
        sb.append('}');
        return sb.toString();
    }
}
