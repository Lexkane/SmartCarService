package ua.ita.smartcarservice.entity.booking;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TimePoint implements Comparable<TimePoint> {

    private LocalDateTime time;

    private boolean position;

    public TimePoint(LocalDateTime time, boolean position){
        this.time = time;
        this.position = position;
    }

    public int compareTo(TimePoint timePoint){
        if(this.time.compareTo(timePoint.time) == 0){
            return this.position ? 1 : -1;
        }

        return this.time.compareTo(timePoint.time);
    }
}
