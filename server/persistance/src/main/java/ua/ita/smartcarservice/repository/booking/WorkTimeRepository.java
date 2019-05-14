package ua.ita.smartcarservice.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.booking.WorkTime;

import java.util.List;

@Repository
public interface WorkTimeRepository extends JpaRepository<WorkTime, Long> {

    @Query("SELECT new ua.ita.smartcarservice.repository.booking.WorkerTasksDto(w.worker.fullName, w.work) FROM WorkTime w WHERE  w.report.id = :id")
    List<WorkerTasksDto> findWorkerTasksByReportId(@Param("id") long reportId);
}
