package ua.ita.smartcarservice.entity.sensors.enums;

public enum SensorProperties {

    ID("id"),
    DATE("date"),
    CAR("car"),
    VALUE("value");

    private String property;

    private SensorProperties(String property) {
        this.property = property;
    }

    public String toString() {
        return property;
    }

}
