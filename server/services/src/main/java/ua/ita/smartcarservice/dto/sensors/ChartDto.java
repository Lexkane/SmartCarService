package ua.ita.smartcarservice.dto.sensors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ua.ita.smartcarservice.entity.sensors.enums.SensorProperties;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChartDto {

    private Map<String, List<Double>> data = new HashMap<>();

    // to create ChartDto with one value of last record in DB
    public ChartDto(Integer value){
        this.data.put(SensorProperties.VALUE.toString(), Arrays.asList((double)value));
    }
}
