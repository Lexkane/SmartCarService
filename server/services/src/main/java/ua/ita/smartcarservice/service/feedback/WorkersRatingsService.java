package ua.ita.smartcarservice.service.feedback;

/**
 * Service interface for WorkersRating.
 */
public interface WorkersRatingsService {
    void addRatingToWorker(Long workerId, Integer rate);

    Double getAvgWorkersRating(Long workerId);
}
