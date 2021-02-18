package training.nadia.hospital.entity;

import java.util.*;

public class Doctor extends User {

    private Set<Patient> patientsUndergoingTreatment;
    private Set<Patient> patientsWhoNeedToBeCheckup;

    {
        patientsUndergoingTreatment = new HashSet<>();
        patientsWhoNeedToBeCheckup = new HashSet<>();
        setRole(Role.DOCTOR);
    }

    public Doctor() {
    }

    public Doctor(String name, String surname) {
        super(name, surname);
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

        return super.equals(doctor);
    }

    @Override
    public int hashCode() {

//        int result = 17;
//        result = 37 * super.hashCode();

        return super.hashCode();
    }
}
