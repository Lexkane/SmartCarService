package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.CoolantEntity;
import ua.ita.smartcarservice.repository.sensors.common.AlertSensorRepository;

@Repository
public interface CoolantRepository extends AlertSensorRepository<CoolantEntity> {
}
