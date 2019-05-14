package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

import java.util.List;

@Data
public class WorkerWithSkillDto {

    private List<String> skillsName;

    private List<String> worksName;

    private Long carId;

}
