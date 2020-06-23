package training.nadia.hospital.entity;

public class Doctor extends User implements MedicalStaff {

    @Override
    public void treat(Patient patient) {
        patient.getTreatment().setActive(true);
    }

    public void prescribeTreatment(Patient patient, TreatmentType treatment) {

        patient.setDiagnosis(Diagnosis.ESTABLISHED);
        patient.setTreatment(treatment);
    }
}
