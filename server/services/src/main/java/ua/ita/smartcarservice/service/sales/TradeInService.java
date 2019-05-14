package ua.ita.smartcarservice.service.sales;

import ua.ita.smartcarservice.dto.sales.CreateTraidInDto;
import ua.ita.smartcarservice.dto.sales.TradeInDto;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.sales.TradeIn;
import ua.ita.smartcarservice.entity.sales.UserSaleManager;

import java.util.List;


public interface TradeInService {

    void createTradeIn(CreateTraidInDto traidInDto);


    void create(String vinNewCar,String vinUsedCar);

    List<TradeIn>tradesIn(String username);

    List<TradeInDto>tradeinDtos(String username);

    void deleteTradeIn(Long id);

    void successTradeIn(Long id);

    TradeIn findTradeIn(String vinNewCar, String vinUsedCar);
}
