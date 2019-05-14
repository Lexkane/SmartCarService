package ua.ita.smartcarservice.entity.sensors.common;


import ua.ita.smartcarservice.entity.Car;

import java.time.LocalDateTime;
import java.util.Map;

public interface ISensor {

    void setDate(LocalDateTime date);

    LocalDateTime getDate();

    Car getCar();

    void setCar(Car car);

    ISensor setValues(ISensor entity, Map<String, Double> values);

}
