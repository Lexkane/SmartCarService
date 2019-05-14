package ua.ita.smartcarservice.entity.sensors;

import lombok.Data;
import ua.ita.smartcarservice.entity.sensors.common.SingleValueSensorEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "fact_speed")
public class SpeedEntity extends SingleValueSensorEntity {

}
