package ua.ita.smartcarservice.entity.sensors;

import lombok.Data;
import ua.ita.smartcarservice.entity.sensors.common.BaseSensorEntity;
import ua.ita.smartcarservice.entity.sensors.common.ISensor;
import ua.ita.smartcarservice.entity.sensors.enums.Tires;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Map;

@Data
@Entity
@Table(name = "fact_tire_pressure")
public class TirePressureEntity extends BaseSensorEntity {

    @Column(nullable = false)
    protected double valueFrontLeft;

    @Column(nullable = false)
    protected double valueFrontRight;

    @Column(nullable = false)
    protected double valueBackLeft;

    @Column(nullable = false)
    protected double valueBackRight;

    public ISensor setValues(ISensor entity, Map<String, Double> values) {
        TirePressureEntity iEntity = (TirePressureEntity) entity;
        iEntity.setValueBackRight(values.get(Tires.BACK_RIGHT.getTireName()));
        iEntity.setValueBackLeft(values.get(Tires.BACK_LEFT.getTireName()));
        iEntity.setValueFrontRight(values.get(Tires.FRONT_RIGHT.getTireName()));
        iEntity.setValueFrontLeft(values.get(Tires.FRONT_LEFT.getTireName()));

        return iEntity;
    }

}
