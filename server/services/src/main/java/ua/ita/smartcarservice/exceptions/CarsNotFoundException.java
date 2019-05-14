package ua.ita.smartcarservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such cars")
public class CarsNotFoundException extends RuntimeException {

    public CarsNotFoundException() {
        super("Could not find cars");
    }

}
