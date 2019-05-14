package ua.ita.smartcarservice.dto.feedback;

import lombok.Data;

/**
 * DTO for ServicesFeedback Entity.\
 * For input from front-end only.
 */
@Data
public class ServicesFeedbackInputDto {
    private String text;
    private String userName;
    private Long serviceId;
}
