package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.BrakeFluidEntity;
import ua.ita.smartcarservice.repository.sensors.common.AlertSensorRepository;

@Repository
public interface BrakeFluidRepository extends AlertSensorRepository<BrakeFluidEntity> {
}
