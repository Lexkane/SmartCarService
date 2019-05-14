package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.MileageEntity;
import ua.ita.smartcarservice.repository.sensors.common.BasicSensorRepository;
import ua.ita.smartcarservice.repository.sensors.common.ChartSensorRepository;

@Repository
public interface MileageRepository extends ChartSensorRepository<MileageEntity>, BasicSensorRepository<MileageEntity> {
}
