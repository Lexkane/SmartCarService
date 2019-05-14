package ua.ita.smartcarservice.repository.feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.feedback.WorkersRatings;

/**
 * JPA Repository for WorkersRating Entity.
 */
public interface WorkersRatingsRepository
        extends JpaRepository<WorkersRatings, Long> {
    @Query("select avg(wr.rate) "
            + "from WorkersRatings as wr "
            + "where wr.userId = :workerId")
    Double getAvgRatingByWorkerId(@Param("workerId") UserEntity workerId);
}
