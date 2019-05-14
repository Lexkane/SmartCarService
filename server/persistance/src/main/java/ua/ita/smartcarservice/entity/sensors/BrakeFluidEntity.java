package ua.ita.smartcarservice.entity.sensors;

import lombok.Data;
import ua.ita.smartcarservice.entity.sensors.common.BaseSensorEntity;
import ua.ita.smartcarservice.entity.sensors.common.ISensor;
import ua.ita.smartcarservice.entity.sensors.enums.BrakeFluid;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Map;

@Data
@Entity
@Table(name = "fact_brake_fluid")
public class BrakeFluidEntity extends BaseSensorEntity {

    @Column(nullable = false)
    protected double level;

    @Column(nullable = false)
    protected double humidity;

    public ISensor setValues(ISensor entity, Map<String, Double> values) {
        BrakeFluidEntity iEntity = (BrakeFluidEntity) entity;
        iEntity.setLevel(values.get(BrakeFluid.LEVEL.toString()));
        iEntity.setHumidity(values.get(BrakeFluid.HUMIDITY.toString()));

        return iEntity;
    }

}
