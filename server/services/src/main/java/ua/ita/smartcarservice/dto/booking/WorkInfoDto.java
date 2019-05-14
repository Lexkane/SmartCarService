package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

@Data
public class WorkInfoDto {

    private Long workId;

    private int start;

    private int end;

    public WorkInfoDto(Long workId, int start, int end){
        this.workId = workId;
        this.start = start;
        this.end = end;
    }

    public WorkInfoDto(){}

}
