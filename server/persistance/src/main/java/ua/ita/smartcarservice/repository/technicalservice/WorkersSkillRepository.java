package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;

import java.util.Collection;
import java.util.List;

/**
 * JPA Repository for WorkersSkill.
 */
@Repository
public interface WorkersSkillRepository
        extends JpaRepository<WorkersSkill, Long> {

    @Query(value = "select s from SkillEntity  s "
            + "left join WorkersSkill as ws "
            + "where ws.workerId = :workerId")
    SkillEntity getSkillByWorkerId(@Param("workerId") UserEntity workerId);

    WorkersSkill getByWorkerId(UserEntity workerId);

    @Query("select ws from WorkersSkill as ws left join WorkType as wt "
            + "on ws.skill = wt.skill "
            + "where wt.workId in (:workId) and "
            + "ws.workerId.id in (:workerId)")
    List<WorkersSkill> findByWorkAndWorker(
            @Param("workerId") Collection<Long> workerId,
            @Param("workId") Collection<Long> workId);
}
