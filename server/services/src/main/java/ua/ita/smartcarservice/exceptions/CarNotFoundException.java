package ua.ita.smartcarservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such car")
public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Long id) {
        super("Could not find car with id " + id);
    }

    public CarNotFoundException(String number) {
        super("Could not find car with number " + number);
    }
}
