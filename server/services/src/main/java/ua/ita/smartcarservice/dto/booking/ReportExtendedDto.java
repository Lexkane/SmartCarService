package ua.ita.smartcarservice.dto.booking;

import lombok.Data;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class ReportExtendedDto {

    private long reportId;

    // User
    private String userFullName;

    // Car
    private String carBrand;
    private String carModel;
    private String carNumber;

    // ReportEntity
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int requiredTime;
    private int price;

    // TechnicalService
    private String technicalServiceName;
    private String technicalServiceAddress;

    private Map<String, List<WorkType>> workerTasks;
}
