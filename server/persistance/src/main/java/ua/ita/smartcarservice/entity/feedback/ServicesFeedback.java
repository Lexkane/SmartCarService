package ua.ita.smartcarservice.entity.feedback;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class ServicesFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String text;

    @Column(nullable = false)
    LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    TechnicalServiceEntity serviceId;

    public ServicesFeedback() {
    }

    public ServicesFeedback(String text, LocalDateTime time, UserEntity userId, TechnicalServiceEntity serviceId) {
        this.text = text;
        this.time = time;
        this.userId = userId;
        this.serviceId = serviceId;
    }
}
