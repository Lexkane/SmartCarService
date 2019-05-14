package ua.ita.smartcarservice.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.SaleManagerEntity;
import ua.ita.smartcarservice.entity.sales.UserSaleManager;


public interface UserSaleManagerRepository extends JpaRepository<UserSaleManager,Long> {

    UserSaleManager findBySaleManagerEntity(SaleManagerEntity saleManagerEntity);

}
