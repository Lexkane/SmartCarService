package ua.ita.smartcarservice.entity.sensors.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum  AggregationFunctions {

    MIN("min"),
    MAX("max"),
    AVG("avg");

    private String function;

    private AggregationFunctions(String function) {
        this.function = function;
    }

    public String toString() {
        return function;
    }

    public static AggregationFunctions getEnumByAggregation(String aggregation){
        return Arrays.stream(AggregationFunctions.values()).filter(s -> s.toString().equals(aggregation))
                .collect(Collectors.toList()).get(0);
    }

}
