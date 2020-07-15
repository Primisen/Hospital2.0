package training.nadia.hospital.entity;

import java.util.Objects;

public class Treatment {

    private boolean active;
    private TreatmentType type;
    private boolean treatmentIsDone;
    private int numberOfTherapies;
    private int numberOfCompletedTherapies;

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

    public boolean isTreatmentIsDone() {
        return treatmentIsDone;
    }

    public void setTreatmentIsDone(boolean treatmentIsDone) {
        this.treatmentIsDone = treatmentIsDone;
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
                treatmentIsDone == treatment.treatmentIsDone &&
                numberOfTherapies == treatment.numberOfTherapies &&
                numberOfCompletedTherapies == treatment.numberOfCompletedTherapies &&
                type == treatment.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, type, treatmentIsDone, numberOfTherapies, numberOfCompletedTherapies);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Treatment{");
        sb.append("active=").append(active);
        sb.append(", type=").append(type);
        sb.append(", treatmentIsDone=").append(treatmentIsDone);
        sb.append(", numberOfTherapies=").append(numberOfTherapies);
        sb.append(", numberOfCompletedTherapies=").append(numberOfCompletedTherapies);
        sb.append('}');
        return sb.toString();
    }
}
