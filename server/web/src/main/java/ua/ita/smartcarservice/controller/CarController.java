package ua.ita.smartcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.NewCarDTO;
import ua.ita.smartcarservice.service.CarService;
import ua.ita.smartcarservice.service.booking.ReportService;

import java.util.List;


@RequestMapping("/api")
@RestController
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private ReportService reportService;

    /* Delete car by id*/
    @DeleteMapping("/car/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        carService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Find car by id*/
    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> findCarById(@PathVariable Long id) {
        CarDto car = carService.findCarById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /* Find all cars */
    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> findAll() {
        List<CarDto> cars = carService.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /* Find owner's cars by user id*/
    @GetMapping("/owner/{id}/cars")
    public ResponseEntity<List<CarDto>> findByUserId(@PathVariable Long id) {
        List<CarDto> cars = carService.findByUserId(id);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /* Find owner's cars by username*/
    @GetMapping("/owner/{username}/car")
    public ResponseEntity<List<CarDto>> findByUsername(@PathVariable String username) {
        List<CarDto> cars = carService.findByUsername(username);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /*  Find car by vin */
    @GetMapping("/car/{vin}/vin")
    public ResponseEntity<CarDto> findByVin(@PathVariable String vin) {
        CarDto car = carService.findByVin(vin);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /*  Find car by number */
    @GetMapping("/car/{number}/number")
    public ResponseEntity<CarDto> findByNumber(@PathVariable String number) {
        CarDto car = carService.findByNumber(number);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /* Create car */
    @PostMapping("/newcar")
    public ResponseEntity addCar(@RequestParam(value = "username") String username,
                                 @RequestBody NewCarDTO newCarDTO) {
        carService.addCar(newCarDTO, username);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
