package ua.ita.smartcarservice.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.DealerEntity;


public interface DealerEntityRepository extends JpaRepository<DealerEntity,Long> {

    DealerEntity findByUserEntityUsername(String username);

    DealerEntity findByDealerEdr(String edr);




}