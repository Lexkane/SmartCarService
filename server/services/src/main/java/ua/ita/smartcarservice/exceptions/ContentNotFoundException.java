package ua.ita.smartcarservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContentNotFoundException extends RuntimeException {

    public ContentNotFoundException(MultipartFile file) {
        super("Content of " + file.getOriginalFilename() + " is empty");
    }
}
