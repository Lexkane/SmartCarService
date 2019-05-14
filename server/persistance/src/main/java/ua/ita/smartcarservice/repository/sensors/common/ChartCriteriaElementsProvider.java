package ua.ita.smartcarservice.repository.sensors.common;

import lombok.Data;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.sensors.enums.SensorProperties;
import ua.ita.smartcarservice.entity.sensors.enums.SensorTypes;
import ua.ita.smartcarservice.entity.sensors.enums.Tires;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


@Data
public class ChartCriteriaElementsProvider {

    private Path<String> datePath;
    private Map<String, Path<Double>> valuePath;
    private Path<Car> carIdPath;

    private long carId;
    private LocalDateTime date;
    private String selection;
    private String aggregation;
    private String sensorType;

    public ChartCriteriaElementsProvider(long carId, LocalDateTime date, String selection, String aggregation,
                                         String sensorType, Root root) {
        this.carId = carId;
        this.date = date;
        this.selection = selection;
        this.aggregation = aggregation;
        this.sensorType = sensorType;
        setPaths(root);
    }

    private void setPaths(Root root) {
        datePath = root.get(SensorProperties.DATE.toString());
        carIdPath = root.get(SensorProperties.CAR.toString()).get(SensorProperties.ID.toString());
        valuePath = getValuePath(root, sensorType);
    }

    private Map<String, Path<Double>> getValuePath(Root root, String sensorType) {
        return Arrays.stream(getValuesNames(sensorType))
                .collect(Collectors.toMap(Object::toString, s -> root.get(s.toString())));
    }

    private Object[] getValuesNames(String sensorType) {
        if (SensorTypes.getEnumBySensorType(sensorType).equals(SensorTypes.TIRE_PRESSURE)) {
            return Tires.values();
        } else {
            return new Object[]{SensorProperties.VALUE.toString()};
        }
    }

}
