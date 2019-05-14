package ua.ita.smartcarservice.service.impl.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.feedback.WorkersRatings;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.feedback.WorkersRatingsRepository;
import ua.ita.smartcarservice.service.feedback.WorkersRatingsService;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Service Implementation for WorkersRating.
 */
@Service
public class WorkersRatingsServiceImpl implements WorkersRatingsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkersRatingsRepository workersRatingsRepository;

    @Override
    public void addRatingToWorker(final Long workerId,
                                  final Integer rate) {
        UserEntity workerEntity;

        workerEntity = userRepository.getUserById(workerId);
        workersRatingsRepository.save(new WorkersRatings(rate, workerEntity));
    }

    @Override
    public Double getAvgWorkersRating(final Long workerId) {
        Double avgRating;
        UserEntity workerEntity;

        workerEntity = userRepository.getUserById(workerId);
        avgRating = workersRatingsRepository.
                getAvgRatingByWorkerId(workerEntity);

        if (avgRating == null) {
            avgRating = 0d;
        }

        return BigDecimal.valueOf(avgRating).
                setScale(1, RoundingMode.HALF_UP).doubleValue();
    }
}
