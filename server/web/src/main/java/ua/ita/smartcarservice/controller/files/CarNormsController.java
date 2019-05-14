package ua.ita.smartcarservice.controller.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.ita.smartcarservice.service.files.CarNormsService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;

@RequestMapping("/api")
@RestController
public class CarNormsController {

    @Autowired
    private CarNormsService carNormsService;

    @PostMapping(value = "/norms", consumes = {"multipart/form-data"})
    public ResponseEntity addNorms (@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file){
        carNormsService.storeCarNorms(file);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
