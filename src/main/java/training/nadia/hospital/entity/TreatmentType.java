package training.nadia.hospital.entity;

public enum TreatmentType {

    PROCEDURE(1, "Процедуры"),
    DRUG(2, "Лекарства");
//    OPERATION(3, "Операция");

    private int id;
    private String russianName;

    TreatmentType(int id, String russianName) {

        this.id = id;
        this.russianName = russianName;
    }

    public int getId() {
        return id;
    }

    public String getRussianName() {
        return russianName;
    }
}
