package ua.ita.smartcarservice.dto.technicalservice;

import lombok.Data;

/**
 * DTO for Worker.
 * Contains SkillEntity DTO.
 */
@Data
public class WorkerSkillDto {
    private Long id;
    private String userName;
    private String fullName;
    private String password;
    private String email;
    private String numberPhone;
    private SkillDto skill;
}
