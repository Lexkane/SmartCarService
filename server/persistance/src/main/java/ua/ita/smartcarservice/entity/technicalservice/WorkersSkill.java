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
 * Entity for special table which connects Worker(User) with Skill.
 */
@Data
@Entity
@Table(name = "workers_skills")
public class WorkersSkill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private UserEntity workerId;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private SkillEntity skill;

    public WorkersSkill() {
    }

    public WorkersSkill(final UserEntity workerId,
                        final SkillEntity skill) {
        this.workerId = workerId;
        this.skill = skill;
    }
}
