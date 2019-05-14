package ua.ita.smartcarservice.service.booking;

import ua.ita.smartcarservice.dto.booking.BookingDto;
import ua.ita.smartcarservice.dto.booking.NewBookingDto;
import ua.ita.smartcarservice.dto.booking.ReportDto;
import ua.ita.smartcarservice.dto.booking.WorkTimeDto;
import ua.ita.smartcarservice.entity.booking.ReportEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    List<LocalDateTime> findTimeToBooking(BookingDto bookingDto);

    void addBooking(NewBookingDto newSessionDto);

    void updateReportsId(ReportDto reportDto, ReportEntity reportEntity);
}
