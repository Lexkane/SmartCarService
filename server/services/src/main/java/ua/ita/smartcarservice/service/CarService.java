package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.NewCarDTO;

import java.util.List;

public interface CarService {

    CarDto findCarById(Long id);

    List<CarDto> findByUserId(Long id);

    List<CarDto> findByUsername(String username);

    CarDto findByNumber(String number);

    CarDto findByVin(String vin);

    List<CarDto> findAll();

    void addCar(NewCarDTO newCarDTO, String username);

    void deleteById(Long id);

    List<CarDto> findbyUserLogin(String login);

    void createByDealer(CarDto carDto, String username);

    List<CarDto> findByDealerEdr(String edr);

    List<CarDto> findAllDealersCars();
}
