package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.TirePressureEntity;
import ua.ita.smartcarservice.repository.sensors.common.BasicSensorRepository;
import ua.ita.smartcarservice.repository.sensors.common.ChartSensorRepository;

@Repository
public interface TirePressureRepository extends ChartSensorRepository<TirePressureEntity>, BasicSensorRepository<TirePressureEntity> {

}
