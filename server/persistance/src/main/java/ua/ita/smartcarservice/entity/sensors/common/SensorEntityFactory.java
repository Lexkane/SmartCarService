package ua.ita.smartcarservice.entity.sensors.common;

import lombok.Data;
import org.springframework.stereotype.Component;
import ua.ita.smartcarservice.entity.sensors.enums.SensorTypes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class SensorEntityFactory {

    private Map<String, Class> entityFactory = new HashMap<>();

    public SensorEntityFactory() {
        entityInit();
    }

    private void entityInit() {
        Arrays.stream(SensorTypes.values())
                .forEach( entity -> entityFactory.put(entity.toString(), entity.getSensorEntity()));
    }

    public ISensor getEntity(String type) {
        try {
            return (ISensor) entityFactory.get(type).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
