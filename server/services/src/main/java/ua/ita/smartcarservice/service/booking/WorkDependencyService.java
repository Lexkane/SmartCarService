package ua.ita.smartcarservice.service.booking;

import ua.ita.smartcarservice.entity.booking.WorkDependency;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;
import ua.ita.smartcarservice.service.impl.booking.WorkInfo;

import java.util.List;
import java.util.Map;

public interface WorkDependencyService {

    List<WorkDependency> findAll();

    WorkInfo findWorkInfo(List <String> skillsName);

    int findGraphSize();

    Map<String, WorkType> getDistinctSkillByName();

}
