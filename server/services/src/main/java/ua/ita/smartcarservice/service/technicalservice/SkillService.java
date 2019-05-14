package ua.ita.smartcarservice.service.technicalservice;

import ua.ita.smartcarservice.dto.technicalservice.SkillDto;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;

import java.util.List;
import java.util.Map;

/**
 * Service Interface for Skill.
 */
public interface SkillService {

    List<SkillDto> getAllSkills();

    SkillDto getSkillDto(SkillEntity skill);

    SkillEntity getSkillById(Long id);

    List<SkillDto> findSkillNameBySto(Long id);

    List<SkillDto> findSkillNameByCarId(Long carId);

    Map<String, SkillEntity> findDistinctSkillByName();

}
