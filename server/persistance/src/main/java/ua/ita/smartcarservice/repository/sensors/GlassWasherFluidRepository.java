package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.GlassWasherFluidEntity;
import ua.ita.smartcarservice.repository.sensors.common.AlertSensorRepository;

@Repository
public interface GlassWasherFluidRepository extends AlertSensorRepository<GlassWasherFluidEntity> {
}
