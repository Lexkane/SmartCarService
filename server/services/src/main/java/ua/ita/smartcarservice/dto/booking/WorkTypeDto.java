package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

@Data
public class WorkTypeDto {

    private String workName;

    private String skillName;

    private Long requiredTime;

    private Long cost;

}
