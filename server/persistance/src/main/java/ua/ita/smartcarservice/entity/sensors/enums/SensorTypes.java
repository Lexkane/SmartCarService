package ua.ita.smartcarservice.entity.sensors.enums;

import ua.ita.smartcarservice.entity.sensors.*;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum SensorTypes {

    SPEED("speed", SpeedEntity.class),
    BATTERY("battery", BatteryEntity.class),
    FUEL("fuel", FuelEntity.class),
    MILEAGE("mileage", MileageEntity.class),
    OIL_PRESSURE("oil pressure", OilPressureEntity.class),
    OIL_LEVEL("oil level", OilLevelEntity.class),

    TIRE_PRESSURE("tire pressure", TirePressureEntity.class),

    GLASS_WASHER_FLUID("glass washer fluid", GlassWasherFluidEntity.class),
    COOLANT("coolant", CoolantEntity.class),
    BRAKE_FLUID("brake fluid", BrakeFluidEntity.class);

    private String sensorType;
    private Class sensorEntity;

    private SensorTypes(String sensorType, Class sensorEntity) {
        this.sensorEntity = sensorEntity;
        this.sensorType = sensorType;
    }

    public String toString() {
        return sensorType;
    }

    public Class getSensorEntity() {
        return sensorEntity;
    }

    public static SensorTypes getEnumBySensorType(String sensorType){
        return Arrays.stream(SensorTypes.values()).filter(s -> s.toString().equals(sensorType))
                .collect(Collectors.toList()).get(0);
    }
}
