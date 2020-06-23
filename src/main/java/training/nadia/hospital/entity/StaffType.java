package training.nadia.hospital.entity;

public enum StaffType {

    DOCTOR(1),
    NURSE(2);

    private int id;

    private StaffType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
