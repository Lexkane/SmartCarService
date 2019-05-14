package ua.ita.smartcarservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Car with such number is registered")
public class CarRegisteredAlreadyExсeption extends RuntimeException {

    public CarRegisteredAlreadyExсeption() {
        super("Car with such registration number or vin already exist");
    }
}
