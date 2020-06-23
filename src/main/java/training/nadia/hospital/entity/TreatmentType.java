package training.nadia.hospital.entity;

public enum TreatmentType {

    PROCEDURE(1),
    DRUG(2),       //лекарства
    OPERATION(3);

    private int id;

    private TreatmentType(int id) {

        this.id = id;
    }

    public int getId() {
        return id;
    }
}
