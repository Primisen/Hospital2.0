package training.nadia.hospital.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor extends User {

    private List<Patient> patientsToCure;
    private List<Patient> patientsToReceive;

    public Doctor() {

        patientsToCure = new ArrayList<>();
        patientsToReceive = new ArrayList<>();
        setRole(Role.DOCTOR);
    }

    public Doctor(String name, String surname) {
        super(name, surname);
    }

    public List<Patient> getPatientsToCure() {
        return patientsToCure;
    }

    public void setPatientsToCure(List<Patient> patientsToCure) {
        this.patientsToCure = patientsToCure;
    }

    public void addPatientToCure(Patient patient) {
        patientsToCure.add(patient);
    }

    public List<Patient> getPatientsToReceive() {
        return patientsToReceive;
    }

    public void setPatientsToReceive(List<Patient> patientsToReceive) {
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
