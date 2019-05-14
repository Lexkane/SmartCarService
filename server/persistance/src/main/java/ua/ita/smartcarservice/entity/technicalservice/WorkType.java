package ua.ita.smartcarservice.entity.technicalservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ua.ita.smartcarservice.entity.alerts.Alerts;
import ua.ita.smartcarservice.entity.booking.WorkDependency;
import ua.ita.smartcarservice.entity.booking.WorkTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Entity for type of work worker can do.
 */
@Entity
@Data
@Table(name = "work_type")
public class WorkType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long requiredTime;

    @Column(nullable = false)
    private Long cost;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private SkillEntity skill;

    @JsonIgnore
    @OneToMany(mappedBy = "mainWork")
    List<WorkDependency> listOfMainWork;

    @JsonIgnore
    @OneToMany(mappedBy = "dependentWork")
    List<WorkDependency> listOfDependentWork;

    @JsonIgnore
    @OneToMany(mappedBy = "work")
    List<WorkTime> workTimes;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
                cascade = {CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH},
                mappedBy = "workType")
    private List<Alerts> alerts;
}

