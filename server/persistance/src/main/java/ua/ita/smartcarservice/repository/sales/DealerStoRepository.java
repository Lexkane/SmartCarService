package ua.ita.smartcarservice.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.DealerSto;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

import java.util.List;


public interface DealerStoRepository extends JpaRepository<DealerSto,Long> {

    List<DealerSto> findAllByDealer(DealerEntity dealerEntity);
    List<DealerSto> findAllByDealer_UserEntityUsername(String username);

    DealerSto findByDealerAndAndTechnicalService(DealerEntity dealerEntity, TechnicalServiceEntity technicalServiceEntity);


}
