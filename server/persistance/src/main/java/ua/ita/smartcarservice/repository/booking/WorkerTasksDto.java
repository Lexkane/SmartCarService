package ua.ita.smartcarservice.repository.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;

@Data
@AllArgsConstructor
public class WorkerTasksDto {

    private String workerFullName;
    
    private WorkType work;

}
