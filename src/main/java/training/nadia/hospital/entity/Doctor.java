package training.nadia.hospital.entity;

import java.util.*;

public class Doctor extends User {

    private Set<Patient> patientsUndergoingTreatment;
    private Set<Patient> patientsWhoNeedToBeCheckup;

    public Doctor() {

        patientsUndergoingTreatment = new HashSet<>();
        patientsWhoNeedToBeCheckup = new HashSet<>();
        setRole(Role.DOCTOR);
    }

    public Doctor(String name, String surname) {
        super(name, surname);
        patientsUndergoingTreatment = new HashSet<>();
        patientsWhoNeedToBeCheckup = new HashSet<>();
        setRole(Role.DOCTOR);
    }

    public Set<Patient> getPatientsUndergoingTreatment() {
        return patientsUndergoingTreatment;
    }

    public void setPatientsUndergoingTreatment(Set<Patient> patientsUndergoingTreatment) {
        this.patientsUndergoingTreatment = patientsUndergoingTreatment;
    }

    public void addPatientsUndergoingTreatment(Patient patient) {
        patientsUndergoingTreatment.add(patient);
    }

    public Set<Patient> getPatientsWhoNeedToBeCheckup() {
        return patientsWhoNeedToBeCheckup;
    }

    public void setPatientsWhoNeedToBeCheckup(Set<Patient> patientsWhoNeedToBeCheckup) {
        this.patientsWhoNeedToBeCheckup = patientsWhoNeedToBeCheckup;
    }

    public void addPatientWhoNeedToBeCheckup(Patient patient) {
        patientsWhoNeedToBeCheckup.add(patient);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Doctor doctor = (Doctor) object;

        return Objects.equals(patientsUndergoingTreatment, doctor.patientsUndergoingTreatment) &&
                Objects.equals(patientsWhoNeedToBeCheckup, doctor.patientsWhoNeedToBeCheckup);
    }

    @Override
    public int hashCode() {

        return super.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Doctor{");
        sb.append("patientsToCure=").append(patientsUndergoingTreatment);
        sb.append(", patientsToReceive=").append(patientsWhoNeedToBeCheckup);
        sb.append('}');
        return sb.toString();
    }
}
