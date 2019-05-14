package ua.ita.smartcarservice.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.ita.smartcarservice.entity.booking.ReportEntity;
import ua.ita.smartcarservice.entity.booking.WorkTime;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<WorkTime, Long> {

    @Query("select w from WorkTime as w where w.worker.id in (:workerId) " +
            "and w.startBooking >= :starttime and w.startBooking <= :endtime")
    List<WorkTime> findTimeWhenWork(@Param("workerId") Collection<Long> workerId,
                                    @Param("starttime") LocalDateTime time,
                                    @Param("endtime") LocalDateTime endt);

    @Transactional
    @Modifying
    @Query("update WorkTime as w " +
            "set w.report = :report " +
            "where w.startBooking >= :starttime and " +
            "w.endBooking <= :endtime and " +
            "w.car.id = :carId")
    void updateReportsId(@Param("starttime") LocalDateTime time,
                         @Param("endtime") LocalDateTime endt,
                         @Param("carId") Long carId,
                         @Param("report") ReportEntity report);

    List<WorkTime> findAllByWorkerId(Long id);

    List<WorkTime> findAllByCarId(Long id);
}
