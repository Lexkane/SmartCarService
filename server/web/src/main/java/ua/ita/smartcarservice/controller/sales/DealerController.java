package ua.ita.smartcarservice.controller.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.NewCarDTO;
import ua.ita.smartcarservice.dto.sales.*;
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.service.CarService;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.sales.ApplyService;
import ua.ita.smartcarservice.service.sales.DealerService;
import ua.ita.smartcarservice.service.sales.TradeInService;
import ua.ita.smartcarservice.service.technicalservice.TechnicalServiceService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 27.02.2019.
 */
//@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping(value = "http://localhost:9501/api/dealer/")
@RestController
public class DealerController {


    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @Autowired
    DealerService dealerService;

    @Autowired
    TechnicalServiceService technicalServiceService;

    @Autowired
    TradeInService tradeInService;

    @Autowired
    ApplyService applyService;
    /* Get all dealers */
    @GetMapping(path = "/api/dealer/getAll")
    public ResponseEntity<List<DealerDto>> getAllDealers() {
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(dealerService.getAllDealerDto());
    }

    /* Get dealer by username*/
    @GetMapping(path = "/api/dealer/get/{username}")
    public ResponseEntity<DealerDto> getDealerDto(@PathVariable String username) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(dealerService.getDealerDtoByUserName(username));

    }

    /* Get dealer by car vin */
    @GetMapping(path = "/api/dealer/getByCarVin/{vin}")
    public ResponseEntity<DealerDto> getDealerByCarVin(@PathVariable String vin) {
        HttpHeaders responseHeaders = new HttpHeaders();
        System.out.println(dealerService.getDealerDtoByByCarVin(vin));
        return ResponseEntity.ok().headers(responseHeaders).body(dealerService.getDealerDtoByByCarVin(vin));

    }

    /* create  dealer */
    @PostMapping(path = "/api/dealer/create/{username}")
    public ResponseEntity<?> createDealer(@PathVariable String username,@RequestBody DealerDto dealerDto) {
        dealerService.createDealer(dealerDto,username);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

        /* edit  dealer */
    @PostMapping(path = "/api/dealer/edit")
    public ResponseEntity<?> editDealer(@RequestBody DealerDto dealerDto) {
        dealerService.editDealerByDealerDto(dealerDto);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }


    /* Get all cars by dealer`s username */
    @GetMapping(path = "/api/dealer/{username}/getAllCar")
    public ResponseEntity<List<DealerCarDto>> getAllDealerCar(@PathVariable String username) {

        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(dealerService.getAllCarDtoByUserNameDealer(username));
    }

    /* create car by dealer */
        @PostMapping("/api/dealer/createcar/{username}")
    public ResponseEntity createDealerCar(@PathVariable String username,@RequestBody CarDto carDto) {
        carService.createByDealer(carDto, username);

        return new ResponseEntity(HttpStatus.CREATED);
    }
    /* create sto by dealer */
    @PutMapping("/api/dealer/createtechservices")
    public ResponseEntity createTechnicalService(@RequestParam(value="username") String username,
                                                 @RequestParam(value = "name") String name,
                                                 @RequestParam(value = "address") String address) {
        technicalServiceService.createTechnicalServiceByDealer(new DealerStoAddDto(name,address),username);
        return new ResponseEntity(HttpStatus.CREATED);

    }

    /* Get dealer's cars */
    @GetMapping("/api/dealer/cars/{username}")
    public ResponseEntity<List<CarDto>> findByDealerUsername(@PathVariable String username) {
        List<CarDto> cars = carService.findbyUserLogin(username);
        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }


    /* Get dealer's cars by edr*/
    @GetMapping("/api/dealer/getAllCars/{edr}")
    public ResponseEntity<List<CarDto>> findByDealerEdr(@PathVariable String edr) {
        List<CarDto> cars = carService.findByDealerEdr(edr);
        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }


    /* Get dealer's sto */
    @GetMapping("/api/dealer/allstos/{username}")
    public ResponseEntity<List<TechnicalServiceDto>> findAllStoByDealer(@PathVariable String username){
        List<TechnicalServiceDto>technicalServiceDtos=technicalServiceService.getAllTechnicalServicesDtoByDealer(username);
        if (technicalServiceDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(technicalServiceDtos, HttpStatus.OK);
    }


    /* Get  only dealers` cars */
    @GetMapping("/api/dealer/allCars")
    public  ResponseEntity<List<CarDto>> getAllDealersCars(){
        List<CarDto>cars=carService.findAllDealersCars();
        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }


    /* create trade in */
    @PostMapping("/api/dealer/createTradeIn")
    public ResponseEntity createTradeIn(@RequestParam(value="vinNewCar") String vinNewCar,
                                        @RequestParam(value = "vinBCar") String vinBCar) {
        tradeInService.create(vinNewCar,vinBCar);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /* Get dealer's trade in by username */
    @GetMapping("/api/dealer/getAllTradeIn/{username}")
    public  ResponseEntity<List<TradeInDto>> getAllDealersTradeIn(@PathVariable String username){
        List<TradeInDto>tradeInDtos=tradeInService.tradeinDtos(username);
        return new ResponseEntity<>(tradeInDtos, HttpStatus.OK);
    }

        /* delete tradeIn by id */
    @DeleteMapping("/api/dealer/delete/{id}")
    public ResponseEntity deleteTradeIn(@PathVariable Long id){
        tradeInService.deleteTradeIn(id);
                return new ResponseEntity<>(HttpStatus.OK);
    }

            /* do tradeIn successed*/
    @DeleteMapping("/api/dealer/successTradeIn/{id}")
    public ResponseEntity successTradeIn(@PathVariable Long id){
        tradeInService.successTradeIn(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

            /* create apply to dealer*/
    @PostMapping("/api/dealer/epplyToDealer")
    public ResponseEntity createEpplyToDealer(@RequestBody ApplyToDealerDto apply){
        applyService.createApplyToDealer(apply);
        return new ResponseEntity(HttpStatus.CREATED);
    }

            /*get all applies to dealers*/
    @GetMapping("/api/dealer/allstosToApply/{username}")
    public  ResponseEntity<List<TechnicalServiceDto>> getAllStotoApply(@PathVariable String username){
List<TechnicalServiceDto>technicalServiceDtos = applyService.technicalServiceDtos(username);
        return new ResponseEntity<>(technicalServiceDtos, HttpStatus.OK);
    }

                /*add sto to dealer with apply */
    @PostMapping("/api/dealer/addStoWithApply")
    public ResponseEntity addStoWithApply(@RequestBody ApplyToDealerDto apply){
        applyService.applyToDealer(apply);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /* ignore sto apply to dealer  */
    @PostMapping("/api/dealer/ignoreApply")
    public ResponseEntity ignoreStoApply(@RequestBody ApplyToDealerDto apply){
        applyService.deleteApplyToDealer(apply);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/api/dealer/deleteSto/{id}")
    public ResponseEntity deleteStoFromDealer(@PathVariable Long id){
dealerService.deleteStoFromDealer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
