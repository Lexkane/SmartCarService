package ua.ita.smartcarservice.service.technicalservice;

import ua.ita.smartcarservice.dto.booking.WorkTypeDto;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;

import java.util.List;
import java.util.Map;

/**
 * Service Interface for WorkType.
 */
public interface WorkTypeService {

    Map<String, WorkType> findDistinctWorkByName();

    Map<String, List<WorkTypeDto>> getAllService(Long id);

}
