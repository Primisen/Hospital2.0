package training.nadia.hospital.entity;

import java.util.Objects;

public class Treatment {

    private boolean active;
    private TreatmentType type;
    private int numberOfTherapies;
    private int numberOfCompletedTherapies;

    public Treatment() {
    }

    public Treatment(int typeId, boolean active) {

        if (typeId == TreatmentType.PROCEDURE.getId()) {
            type = TreatmentType.PROCEDURE;

        } else if (typeId == TreatmentType.DRUG.getId()) {
            type = TreatmentType.DRUG;

        } else if (typeId == TreatmentType.OPERATION.getId()) {
            type = TreatmentType.OPERATION;
        }

        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TreatmentType getType() {
        return type;
    }

    public void setType(TreatmentType type) {
        this.type = type;
    }

    public void setType(int id) {

        if (id == TreatmentType.PROCEDURE.getId()) {
            type = TreatmentType.PROCEDURE;
        } else if (id == TreatmentType.DRUG.getId()) {
            type = TreatmentType.DRUG;
        } else if (id == TreatmentType.OPERATION.getId()) {
            type = TreatmentType.OPERATION;
        }
    }

    public int getNumberOfTherapies() {
        return numberOfTherapies;
    }

    public void setNumberOfTherapies(int numberOfTherapies) {
        this.numberOfTherapies = numberOfTherapies;
    }

    public int getNumberOfCompletedTherapies() {
        return numberOfCompletedTherapies;
    }

    public void setNumberOfCompletedTherapies(int numberOfCompletedTherapies) {
        this.numberOfCompletedTherapies = numberOfCompletedTherapies;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Treatment treatment = (Treatment) object;
        return active == treatment.active &&
                numberOfTherapies == treatment.numberOfTherapies &&
                numberOfCompletedTherapies == treatment.numberOfCompletedTherapies &&
                type == treatment.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, type, numberOfTherapies, numberOfCompletedTherapies);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Treatment{");
        sb.append("active=").append(active);
        sb.append(", type=").append(type);
        sb.append(", numberOfTherapies=").append(numberOfTherapies);
        sb.append(", numberOfCompletedTherapies=").append(numberOfCompletedTherapies);
        sb.append('}');
        return sb.toString();
    }
}
