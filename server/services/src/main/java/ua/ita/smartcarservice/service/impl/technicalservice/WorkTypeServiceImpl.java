package ua.ita.smartcarservice.service.impl.technicalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.WorkTypeDto;
import ua.ita.smartcarservice.dto.technicalservice.SkillDto;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;
import ua.ita.smartcarservice.repository.technicalservice.WorkTypeRepository;
import ua.ita.smartcarservice.service.technicalservice.SkillService;
import ua.ita.smartcarservice.service.technicalservice.WorkTypeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of WorkType Service.
 */
@Service
public final class WorkTypeServiceImpl implements WorkTypeService {

    @Autowired
    private WorkTypeRepository workTypeRepository;

    @Autowired
    private SkillService skillService;

    @Override
    public Map<String, WorkType> findDistinctWorkByName() {
        return workTypeRepository.findAll().stream()
            .collect(Collectors.toMap(WorkType::getName, workType -> workType));
    }

    /**
     * Method which returns info about work
     * with user can choose in his technical service.
     *
     * @param id - car Id
     * @return Map this contains key - skillName and value - list of workInfo
     */
    @Override
    public Map<String, List<WorkTypeDto>> getAllService(final Long id) {
        return skillService.findSkillNameByCarId(id).stream()
                .collect(Collectors.toMap(SkillDto::getName,
                        skillDto -> workTypeRepository.
                                findAllWorkBySkill(skillDto.getName())
                                    .stream().map(this::getDto).
                                        collect(Collectors.toList())));
    }

    private WorkTypeDto getDto(final WorkType workType) {
        WorkTypeDto workTypeDto = new WorkTypeDto();
        workTypeDto.setWorkName(workType.getName());
        workTypeDto.setCost(workType.getCost());
        workTypeDto.setRequiredTime(workType.getRequiredTime());
        workTypeDto.setSkillName(workType.getSkill().getName());

        return workTypeDto;
    }

}
