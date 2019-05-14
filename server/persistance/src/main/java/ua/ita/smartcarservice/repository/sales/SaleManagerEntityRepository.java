package ua.ita.smartcarservice.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.sales.SaleManagerEntity;
import ua.ita.smartcarservice.entity.sales.UserSaleManager;

import java.util.List;

public interface SaleManagerEntityRepository extends JpaRepository<SaleManagerEntity,Long> {

    SaleManagerEntity getByUserSaleManagers(UserSaleManager userSaleManager);

    List<SaleManagerEntity> getAllByDealerEntity(DealerEntity dealerEntity);

}