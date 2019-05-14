package ua.ita.smartcarservice.entity.technicalservice;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Entity for special table which connects User and TechnicalService.
 */
@Data
@Entity
@Table(name = "users_service")
public class UserTechnicalService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "technical_service_id", nullable = false)
    private TechnicalServiceEntity technicalServiceId;

    public UserTechnicalService() {

    }

    public UserTechnicalService(final UserEntity userId,
                            final TechnicalServiceEntity technicalServiceId) {
        this.userId = userId;
        this.technicalServiceId = technicalServiceId;
    }
}
