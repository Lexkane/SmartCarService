package ua.ita.smartcarservice.entity.feedback;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;

import javax.persistence.*;

@Data
@Entity
public class FeedbackToLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    UserEntity workerId;

    public FeedbackToLeave() { }

    public FeedbackToLeave(UserEntity userId, UserEntity workerId) {
        this.userId = userId;
        this.workerId = workerId;
    }
}
