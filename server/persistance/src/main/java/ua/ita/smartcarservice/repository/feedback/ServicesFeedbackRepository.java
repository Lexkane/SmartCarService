package ua.ita.smartcarservice.repository.feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.feedback.ServicesFeedback;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

import java.util.List;

/**
 * JPA Repository for Services Feedback.
 */
public interface ServicesFeedbackRepository
        extends JpaRepository<ServicesFeedback, Long> {
    List<ServicesFeedback> getByUserIdOrderByTimeAsc(UserEntity userEntity);

    List<ServicesFeedback> getByServiceIdOrderByTimeAsc(
                            TechnicalServiceEntity technicalServiceEntity);

    @Query("select avg(wr.rate) from WorkersRatings as wr "
            + "left join UserTechnicalService as uts "
            + "on wr.userId = uts.userId "
            + "where uts.technicalServiceId = :technicalServiceId")
    Double getServicesRating(
        @Param("technicalServiceId") TechnicalServiceEntity technicalServiceId);
}
