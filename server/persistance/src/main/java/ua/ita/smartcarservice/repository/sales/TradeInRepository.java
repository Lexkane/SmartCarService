package ua.ita.smartcarservice.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.sales.TradeIn;

import java.util.List;


public interface TradeInRepository extends JpaRepository<TradeIn,Long> {

        List<TradeIn>findAllByDealerAndIsactive(DealerEntity dealerEntity,String isActive);

//        TradeIn findByIdDealerAndIdUserAndVinNewCarAndVinUsedCar(Long dealerId,Long userId,String vinNewCar,String vinUsedCar);
TradeIn findByIdDealerAndIdUserAndVinNewCarAndVinUsedCarAndIsactive(Long dealerId,Long userId,String vinNewCar,String vinUsedCar,String active);
}