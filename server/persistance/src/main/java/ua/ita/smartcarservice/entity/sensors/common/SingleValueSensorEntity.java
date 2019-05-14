package ua.ita.smartcarservice.entity.sensors.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class SingleValueSensorEntity extends BaseSensorEntity implements ISingleValueSensor {

    @Column(nullable = false)
    protected double value;

}