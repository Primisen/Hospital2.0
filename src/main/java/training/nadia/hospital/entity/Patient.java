package training.nadia.hospital.entity;

import java.util.Objects;

public class Patient extends User {

    private String diagnosis;
    private Treatment treatment;
    private Doctor treatingDoctor;
    private Doctor receptionDoctor;

    public Patient() {
        this.treatment = new Treatment();
        setRole(Role.PATIENT);
    }

    public Patient(String name, String surname) {
        super(name, surname);
        treatment = new Treatment();
        setRole(Role.PATIENT);
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public Doctor getTreatingDoctor() {
        return treatingDoctor;
    }

    public void setTreatingDoctor(Doctor treatingDoctor) {
        this.treatingDoctor = treatingDoctor;
    }

    public Doctor getReceptionDoctor() {
        return receptionDoctor;
    }

    public void setReceptionDoctor(Doctor receptionDoctor) {
        this.receptionDoctor = receptionDoctor;
    }

    @Override
    public int hashCode() {

        int result = 17;

        result = 37 * result + super.hashCode();
        result = 37 * result + (diagnosis == null ? 0 : diagnosis.hashCode());
        result = 37 * result + (treatment == null ? 0 : treatment.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Patient patient = (Patient) object;
        return Objects.equals(diagnosis, patient.diagnosis) &&
                Objects.equals(treatment, patient.treatment) &&
                Objects.equals(treatingDoctor, patient.treatingDoctor) &&
                Objects.equals(receptionDoctor, patient.receptionDoctor);
    }
}
