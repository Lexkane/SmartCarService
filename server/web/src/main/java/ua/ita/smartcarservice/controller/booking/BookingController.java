package ua.ita.smartcarservice.controller.booking;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.booking.*;
import ua.ita.smartcarservice.service.booking.BookingService;
import ua.ita.smartcarservice.service.technicalservice.TechnicalServiceService;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping(value = "/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TechnicalServiceService technicalService;

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    @PostMapping("/time")
    public ResponseEntity<BookingInfoDto> findTimeToBooking(@RequestBody BookingDto bookingDto) {
        return new ResponseEntity <>(new BookingInfoDto(bookingService.findTimeToBooking(bookingDto),
                technicalService.findTechnicalServiceByCarId(bookingDto.getCarId())),
                HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity addBooking(@RequestBody NewBookingDto newBookingDto) {
        logger.info("Trying to add new booking for car with id: " + newBookingDto.getCarId());
        try {
            bookingService.addBooking(newBookingDto);
            logger.info("Successfully added new booking for car with id: " + newBookingDto.getCarId());
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            logger.error("Can not add new booking for car with id : " + newBookingDto.getCarId());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

}