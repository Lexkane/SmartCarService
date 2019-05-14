package ua.ita.smartcarservice.service.booking;

import ua.ita.smartcarservice.entity.technicalservice.WorkType;

import java.util.List;
import java.util.Map;

public interface WorkTimeService {

    Map<String, List<WorkType>> findWorkerTasksByReportId(long reportId);

}
