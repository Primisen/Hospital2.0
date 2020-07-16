package training.nadia.hospital.entity;

import java.util.Objects;

public class Patient extends User {

    private String diagnosis;
    private Treatment treatment;
    private Doctor treatingDoctor;
    private Doctor receivingDoctor;

    public Patient() {
        this.treatment = new Treatment();
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
    }

    public Doctor getTreatingDoctor() {
        return treatingDoctor;
    }

    public void setTreatingDoctor(Doctor treatingDoctor) {
        this.treatingDoctor = treatingDoctor;
    }

    public Doctor getReceivingDoctor() {
        return receivingDoctor;
    }

    public void setReceivingDoctor(Doctor receivingDoctor) {
        this.receivingDoctor = receivingDoctor;
    }

    @Override
    public int getRoleId(){
        return Role.PATIENT.getId();
    }

    @Override
    public int hashCode() {

        int prime = 31;
        int result = 1;

        result = prime * result + (diagnosis == null ? 0 : diagnosis.hashCode());
        result = prime * result + (treatment == null ? 0 : treatment.hashCode());
        result = prime * result + (treatingDoctor == null ? 0 : treatingDoctor.hashCode());
        result = prime * result + (receivingDoctor == null ? 0 : receivingDoctor.hashCode());

        return result;
    }

//    @Override
//    public boolean equals(Object object) {
//
//        if (object == this) {
//            return true;
//
//        } else if (object == null || object.getClass() != this.getClass()) {
//            return false;
//        }
//
//        Patient patient = (Patient) object;
//
//        return
//                diagnosis.equals(patient.diagnosis) &&
//                        treatment.equals(patient.treatment) &&
//                        receivingDoctor.equals(patient.receivingDoctor) &&
//                        treatingDoctor.equals(patient.treatingDoctor);
//    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Patient patient = (Patient) object;
        return Objects.equals(diagnosis, patient.diagnosis) &&
                Objects.equals(treatment, patient.treatment) &&
                Objects.equals(treatingDoctor, patient.treatingDoctor) &&
                Objects.equals(receivingDoctor, patient.receivingDoctor);
    }
}
