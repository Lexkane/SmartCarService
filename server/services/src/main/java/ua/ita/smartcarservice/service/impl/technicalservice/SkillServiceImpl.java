package ua.ita.smartcarservice.service.impl.technicalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.technicalservice.SkillDto;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.repository.technicalservice.SkillRepository;
import ua.ita.smartcarservice.service.technicalservice.SkillService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of Skill service.
 */
@Service
public final class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<SkillDto> getAllSkills() {
        List<SkillDto> allSkill = new ArrayList<>();

        skillRepository.findAll().parallelStream().forEach(skill -> {
            allSkill.add(getSkillDto(skill));
        });

        return allSkill;
    }

    @Override
    public Map<String, SkillEntity> findDistinctSkillByName() {
        return skillRepository.findAll().stream().
            collect(Collectors.toMap(SkillEntity::getName, s -> s));
    }

    @Override
    public SkillDto getSkillDto(final SkillEntity skill) {
        SkillDto skillDto = new SkillDto();
        skillDto.setId(skill.getSkillId());
        skillDto.setName(skill.getName());
        return skillDto;
    }

    @Override
    public List<SkillDto> findSkillNameBySto(final Long id) {
        List<SkillDto> skillBySto = new ArrayList<>();

        skillRepository.findSkillNameBySto(id).
            forEach(skillEntity -> skillBySto.add(getSkillDto(skillEntity)));

        return skillBySto;
    }

    /**
     * This method allows get list of skill entity by car id.
     *
     * @param carId - car Id
     * @return - return all skills entity for car
     */
    @Override
    public List<SkillDto> findSkillNameByCarId(final Long carId) {
        return skillRepository.findSkillNameByCarId(carId).
                stream().map(this::getSkillDto).collect(Collectors.toList());
    }

    @Override
    public SkillEntity getSkillById(final Long id) {
        return skillRepository.findById(id).get();
    }
}
