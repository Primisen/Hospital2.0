package training.nadia.hospital.entity;

import java.util.List;
import java.util.Objects;

public class Doctor extends User implements MedicalStaff {

    private StaffType staffType = StaffType.DOCTOR;

    private List<Patient> patientsToCure;
    private List<Patient> patientsToReceive;

    @Override
    public StaffType getStaffType() {
        return staffType;
    }

    public List<Patient> getPatientsToCure() {
        return patientsToCure;
    }

    public void setPatientsToCure(List<Patient> patientsToCure) {
        this.patientsToCure = patientsToCure;
    }

    public List<Patient> getPatientsToReceive() {
        return patientsToReceive;
    }

    public void setPatientsToReceive(List<Patient> patientsToReceive) {
        this.patientsToReceive = patientsToReceive;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Doctor doctor = (Doctor) object;
        return staffType == doctor.staffType &&
                Objects.equals(patientsToCure, doctor.patientsToCure) &&
                Objects.equals(patientsToReceive, doctor.patientsToReceive);
    }

    @Override
    public int getRoleId(){ //! такой же есть и у медсетсры
        return Role.MEDICAL_STAFF.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffType, patientsToCure, patientsToReceive);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Doctor{");
        sb.append("patientsToCure=").append(patientsToCure);
        sb.append(", patientsToReceive=").append(patientsToReceive);
        sb.append('}');
        return sb.toString();
    }
}
