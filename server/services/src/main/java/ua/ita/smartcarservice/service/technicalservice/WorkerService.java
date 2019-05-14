package ua.ita.smartcarservice.service.technicalservice;

import ua.ita.smartcarservice.dto.booking.WorkerDto;
import ua.ita.smartcarservice.dto.technicalservice.WorkerSkillDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

import java.util.List;

/**
 * Service Interface For Worker.
 */
public interface WorkerService {

    void saveWorker(UserEntity workerEntity, Long skillId,
                    TechnicalServiceEntity technicalServiceEntity);

    void updateWorker(UserEntity workerEntity, SkillEntity skillEntity);

    void deleteWorker(Long workerId) throws Exception;

    List<WorkerDto> findByCarIdAndWorkersSkill(String name, Long carId);

    void addSkillToWorker(String username, Long skillId);

    List<WorkerSkillDto> addSkillToWorkersList(List<UserEntity> workersList);

    UserEntity getWorkerById(Long id);

    WorkerSkillDto getWorkerSkillDtoById(Long workerId);

    List<UserEntity> getAllWorkers();

}
