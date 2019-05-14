package ua.ita.smartcarservice.service.feedback;

import java.util.List;

/**
 * Service Interface for feedback user have to leave.
 */
public interface FeedbackToLeaveService {
    /**
     * Implementation should save created Entity to DB using JPA Repository.
     * @param username
     * @param workerId
     */
    void saveFeedbackToLeave(String username, List<Long> workerId);

    List<Long> findWorkersIdToLeaveFeedbackByUsername(String username);

    void deleteByUsername(String username);
}
