package ua.ita.smartcarservice.repository.feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.feedback.FeedbackToLeave;

import java.util.List;

/**
 * JPA repository for Services Feedback entity.
 */
public interface FeedbackToLeaveRepository
        extends JpaRepository<FeedbackToLeave, Long> {
    List<FeedbackToLeave> findAllByUserId(UserEntity userId);

    void deleteAllByUserId(UserEntity userId);
}
