package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.FuelEntity;
import ua.ita.smartcarservice.repository.sensors.common.AlertSensorRepository;
import ua.ita.smartcarservice.repository.sensors.common.ChartSensorRepository;

@Repository
public interface FuelRepository extends ChartSensorRepository<FuelEntity>, AlertSensorRepository<FuelEntity> {
}
