package training.nadia.hospital.entity;

public class Patient extends User {

    private Diagnosis diagnosis;
    private Treatment treatment;
    private boolean treatmentIsDone;

    public Patient() {

        this.treatment = new Treatment();
        diagnosis = Diagnosis.NOT_ESTABLISHED;
        treatmentIsDone = false;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(TreatmentType treatment) {

        if (diagnosis == Diagnosis.ESTABLISHED) {

            this.treatment.setType(treatment);
        }
    }

    public boolean isTreatmentIsDone() {
        return treatmentIsDone;
    }

    public void setTreatmentIsDone(boolean treatmentIsDone) {
        if (getTreatment().getType() != null) {
            this.treatmentIsDone = treatmentIsDone;
        }
    }
}
