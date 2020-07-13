package training.nadia.hospital.entity;

import java.util.Objects;

public class Treatment {

    private boolean active;
    private TreatmentType type;
    private boolean treatmentIsDone;

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Treatment treatment = (Treatment) object;
        return active == treatment.active &&
                treatmentIsDone == treatment.treatmentIsDone &&
                type == treatment.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, type, treatmentIsDone);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Treatment{");
        sb.append("active=").append(active);
        sb.append(", type=").append(type);
        sb.append(", treatmentIsDone=").append(treatmentIsDone);
        sb.append('}');
        return sb.toString();
    }
}
