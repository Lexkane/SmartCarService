package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

import java.util.List;

@Data
public class BookingDto {

    List<String> workersId;

    String time;

    Long carId;

    Integer needTime;

}
