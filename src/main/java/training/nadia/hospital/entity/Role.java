package training.nadia.hospital.entity;

public enum Role {

    PATIENT(1),
    DOCTOR(4),
    NURSE(5);

    private int id;

    private Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
