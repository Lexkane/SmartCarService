package ua.ita.smartcarservice.entity.sensors.common;


import java.util.Map;

public interface ISingleValueSensor extends ISensor {

    void setValue(double value);

    double getValue();

    default ISensor setValues(ISensor entity, Map<String, Double> values) {
        ISingleValueSensor iEntity = (ISingleValueSensor) entity;
        iEntity.setValue(values.get("value"));

        return iEntity;
    }

}
