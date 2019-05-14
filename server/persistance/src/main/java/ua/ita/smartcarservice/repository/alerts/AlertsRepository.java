package ua.ita.smartcarservice.repository.alerts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.alerts.Alerts;

@Repository
public interface AlertsRepository extends JpaRepository<Alerts, Long>{

	@Query ("SELECT a FROM Alerts a WHERE a.alertCode = :alertCode")
	Alerts getAlert (@Param ("alertCode") String alertCode);

}
