package ua.ita.smartcarservice.service.impl.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;
import ua.ita.smartcarservice.repository.booking.WorkTimeRepository;
import ua.ita.smartcarservice.repository.booking.WorkerTasksDto;
import ua.ita.smartcarservice.service.booking.WorkTimeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WorkTimeServiceImpl implements WorkTimeService {

    @Autowired
    WorkTimeRepository workTimeRepository;

    public Map<String, List<WorkType>> findWorkerTasksByReportId(long reportId) {
        return workTimeRepository.findWorkerTasksByReportId(reportId)
                .stream().collect(Collectors.groupingBy(WorkerTasksDto::getWorkerFullName))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                    e -> e.getValue().stream().map(WorkerTasksDto::getWork).collect(Collectors.toList())));
    }
}

