package ua.ita.smartcarservice.service.sales;

import ua.ita.smartcarservice.dto.sales.DealerCarDto;
import ua.ita.smartcarservice.dto.sales.DealerDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.sales.*;

import java.util.List;

public interface DealerService {

    DealerDto getDealerDtoByByCarVin(String vin);

    void createDealer(DealerDto dealerDto, String username);

    DealerEntity getDealerEntityByUserName(String Username);

    void createDealer(DealerEntity dealerEntity);

    List<DealerEntity> getAllDealers();

    DealerDto getDealerDtoByUserName(String username);

    DealerEntity dealerDtoToEntity(DealerDto dealerDto);

    void editDealerEntityByEntity(DealerEntity dealerEntity);

    void editDealerByDealerDto(DealerDto dealerDto);

    List<DealerDto> getAllDealerDto();

    List<DealerCarDto> getAllCarDtoByUserNameDealer(String username);

    void deleteStoFromDealer(Long id);
}
