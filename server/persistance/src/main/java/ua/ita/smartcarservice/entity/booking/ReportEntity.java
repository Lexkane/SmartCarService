package ua.ita.smartcarservice.entity.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "reports")
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "technical_service_id")
    private TechnicalServiceEntity technicalService;

    @JsonIgnore
    @OneToMany(mappedBy = "report")
    private List<WorkTime> workTimes;

    @Column(nullable = false, columnDefinition = "DATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Column(nullable = false, columnDefinition = "DATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Column
    private Integer requiredTime;

    @Column
    private Integer price;

    public ReportEntity(ReportEntity report, Car car, TechnicalServiceEntity technicalService) {
        this.car = car;
        this.technicalService = technicalService;
        this.reportId = report.reportId;
        this.startTime = report.startTime;
        this.endTime = report.endTime;
        this.requiredTime = report.requiredTime;
        this.price = report.price;
    }
}