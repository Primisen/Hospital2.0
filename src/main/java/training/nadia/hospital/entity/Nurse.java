package training.nadia.hospital.entity;

public class Nurse extends User implements MedicalStaff {

    @Override
    public void treat(Patient patient) {

        if (treatmentIsNotOperation(patient)) {
            patient.getTreatment().setActive(true);
        }
    }

    private boolean treatmentIsNotOperation(Patient patient) {
        return patient.getTreatment().getType() != TreatmentType.OPERATION;
    }
}
