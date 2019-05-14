package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.SpeedEntity;
import ua.ita.smartcarservice.repository.sensors.common.BasicSensorRepository;
import ua.ita.smartcarservice.repository.sensors.common.ChartSensorRepository;

@Repository
public interface SpeedRepository extends ChartSensorRepository<SpeedEntity>, BasicSensorRepository<SpeedEntity> {
}
