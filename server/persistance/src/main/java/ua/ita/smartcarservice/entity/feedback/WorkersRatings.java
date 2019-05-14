package ua.ita.smartcarservice.entity.feedback;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;

import javax.persistence.*;

@Data
@Entity
public class WorkersRatings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Integer rate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    public WorkersRatings() {
    }

    public WorkersRatings(Integer rate, UserEntity userId) {
        this.rate = rate;
        this.userId = userId;
    }
}
