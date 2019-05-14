package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingInfoDto {

    LocalDateTime startBooking;

    LocalDateTime endBooking;

    String techServiceName;

    public BookingInfoDto(List<LocalDateTime> bookingTime, String techServiceName){
        this.startBooking = bookingTime.get(0);
        this.endBooking = bookingTime.get(1);
        this.techServiceName = techServiceName;
    }

}
