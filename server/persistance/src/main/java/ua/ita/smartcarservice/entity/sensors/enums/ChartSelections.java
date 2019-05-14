package ua.ita.smartcarservice.entity.sensors.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum ChartSelections {

    YEAR("year"),
    MONTH("month"),
    DAY("day"),
    TIME("time"),

    LAST("last");

    private String period;

    ChartSelections(String period) {
        this.period = period;
    }

    public String toString() {
        return period;
    }

    public static ChartSelections getEnumByPeriod(String period){
        return Arrays.stream(ChartSelections.values()).filter(s -> s.toString().equals(period))
                .collect(Collectors.toList()).get(0);
    }

}
