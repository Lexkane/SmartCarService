package ua.ita.smartcarservice.dto.feedback;

import lombok.Data;

/**
 * DTO for Services Feedback Entity.
 * For output only.
 */
@Data
public class ServicesFeedbackOutputDto {
    private Long id;

    private String text;

    private String time;

    private String fullName;

    private String serviceName;

    public ServicesFeedbackOutputDto(final Long id,
                                     final String text,
                                     final String time,
                                     final String fullName,
                                     final String serviceName) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.fullName = fullName;
        this.serviceName = serviceName;
    }
}
