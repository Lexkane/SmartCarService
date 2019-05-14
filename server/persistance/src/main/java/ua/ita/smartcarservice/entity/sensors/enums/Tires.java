package ua.ita.smartcarservice.entity.sensors.enums;

public enum Tires {
    FRONT_LEFT("frontLeft", "valueFrontLeft"),
    FRONT_RIGHT("frontRight", "valueFrontRight"),
    BACK_LEFT("backLeft", "valueBackLeft"),
    BACK_RIGHT("backRight", "valueBackRight");

    private String tireName;
    private String valueName;

    private Tires(String tireName, String valueName) {
        this.tireName = tireName;
        this.valueName = valueName;
    }

    public String toString()  {
        return valueName;
    }

    public String getTireName() {
        return tireName;
    }
}
