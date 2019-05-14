package ua.ita.smartcarservice.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.booking.ReportEntity;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

    @Query("SELECT r FROM ReportEntity r " +
            "JOIN Car c ON r.car.id = c.id WHERE c.user.id = :id")
    List<ReportEntity> findAllReportsByUserId(@Param("id") long userId);

    @Query("SELECT r FROM ReportEntity r WHERE r.car.id = :id")
    List<ReportEntity> findAllReportsByCarId(@Param("id") long carId);

}
