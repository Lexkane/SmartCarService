package ua.ita.smartcarservice.service.impl.booking;

import lombok.Data;
import ua.ita.smartcarservice.dto.booking.WorkInfoDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class WorkInfo {

    private List<WorkInfoDto> workInfo = new ArrayList <>();

    private int requiredTime;

    public void setWorkInfo(WorkInfoDto workInfo){
        this.workInfo.add(workInfo);
    }
}
