package ua.ita.smartcarservice.entity.booking;

import lombok.Data;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;

import javax.persistence.*;

@Data
@Entity
@Table(name = "work_dependency")
public class WorkDependency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dependencyId;

    @ManyToOne
    @JoinColumn(name = "main_work", nullable = false)
    private WorkType mainWork;

    @ManyToOne
    @JoinColumn(name = "dependent_work", nullable = false)
    private WorkType dependentWork;

}
