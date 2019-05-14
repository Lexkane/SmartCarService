package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;

import java.util.List;

/**
 * Repository for CRUD operations with Skill.
 */
@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    /**
     * Method with custom HQL query for finding all skills of TechnicalService.
     * @param stoId id of TechnicalService.
     * @return List of Skill Entities.
     */
    @Query("select distinct w.skill from WorkersSkill as w "
            + "left join UserTechnicalService as ut on ut.userId = w.workerId "
            + "where ut.technicalServiceId.technicalServiceId = :stoId")
    List<SkillEntity> findSkillNameBySto(@Param("stoId") Long stoId);

    /**
     * Method with custom HQL query for finding
     * all skills of TechnicalService by Car.
     * @param id id of Car.
     * @return List of Skill Entities.
     */
    @Query("select distinct w.skill from WorkersSkill as w "
            + "left join UserTechnicalService as ut on w.workerId = "
            + "(select ut.userId from UserTechnicalService as ut "
            + "left join Car as c on ut.userId = c.user "
            + "where c.id = :id)")
    List<SkillEntity> findSkillNameByCarId(@Param("id") Long id);
}
