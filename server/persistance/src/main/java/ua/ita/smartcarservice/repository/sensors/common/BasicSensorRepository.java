package ua.ita.smartcarservice.repository.sensors.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ua.ita.smartcarservice.entity.sensors.common.BaseSensorEntity;

@NoRepositoryBean
public interface BasicSensorRepository<T extends BaseSensorEntity> extends JpaRepository<T, Long> {

}

