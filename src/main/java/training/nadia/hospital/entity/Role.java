package training.nadia.hospital.entity;

public enum Role {

    PATIENT(1),
    MEDICAL_STAFF(2),
    ADMINISTRATOR(3);

    private int id;

    private Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
