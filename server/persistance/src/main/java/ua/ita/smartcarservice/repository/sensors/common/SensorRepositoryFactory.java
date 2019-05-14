package ua.ita.smartcarservice.repository.sensors.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ita.smartcarservice.entity.sensors.common.BaseSensorEntity;
import ua.ita.smartcarservice.entity.sensors.enums.SensorTypes;
import ua.ita.smartcarservice.repository.sensors.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class SensorRepositoryFactory {

    @Autowired
    private SpeedRepository speedRepository;

    @Autowired
    private BatteryRepository batteryRepository;

    @Autowired
    private FuelRepository fuelRepository;

    @Autowired
    private MileageRepository mileageRepository;

    @Autowired
    private OilPressureRepository oilPressureRepository;

    @Autowired
    private OilLevelRepository oilLevelRepository;

    @Autowired
    private TirePressureRepository tirePressureRepository;

    @Autowired
    private BrakeFluidRepository brakeFluidRepository;

    @Autowired
    private CoolantRepository coolantRepository;

    @Autowired
    private GlassWasherFluidRepository glassWasherFluidRepository;


    private Map<String, BasicSensorRepository<? extends BaseSensorEntity>> repositoryFactory = new HashMap<>();

    public BasicSensorRepository<? extends BaseSensorEntity> getRepository(String type) {
        if (repositoryFactory.size() == 0) {
            repositoryInit();
        }
        return repositoryFactory.get(type);
    }

    private void repositoryInit() {
        repositoryFactory.put(SensorTypes.SPEED.toString(), speedRepository);
        repositoryFactory.put(SensorTypes.BATTERY.toString(), batteryRepository);
        repositoryFactory.put(SensorTypes.FUEL.toString(), fuelRepository);
        repositoryFactory.put(SensorTypes.MILEAGE.toString(), mileageRepository);
        repositoryFactory.put(SensorTypes.OIL_PRESSURE.toString(), oilPressureRepository);
        repositoryFactory.put(SensorTypes.OIL_LEVEL.toString(), oilLevelRepository);
        repositoryFactory.put(SensorTypes.TIRE_PRESSURE.toString(), tirePressureRepository);
        repositoryFactory.put(SensorTypes.BRAKE_FLUID.toString(), brakeFluidRepository);
        repositoryFactory.put(SensorTypes.COOLANT.toString(), coolantRepository);
        repositoryFactory.put(SensorTypes.GLASS_WASHER_FLUID.toString(), glassWasherFluidRepository);
    }

}
