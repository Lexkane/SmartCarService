package ua.ita.smartcarservice.entity.technicalservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Entity for Skill of Worker.
 */
@Data
@Entity
@Table(name = "skills")
public class SkillEntity {

    /**
     * Id of skill.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    /**
     * Name of skill.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * List of workers with this skill.
     * Not included to JSON file.
     * Are not a member of final object.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "workerId")
    private List<WorkersSkill> workersSkill;

    /**
     * List of types of works,
     * worker with this skill can do.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "skill")
    private List<WorkType> workTypes;

    /**
     * Non parametrised constructor.
     */
    public SkillEntity() {
    }

    /**
     * Required parameterised constructor.
     *
     * @param skillName name of skill.
     */
    public SkillEntity(final String skillName) {
        this.name = skillName;
    }
}
