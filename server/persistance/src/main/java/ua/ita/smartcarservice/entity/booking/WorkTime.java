package ua.ita.smartcarservice.entity.booking;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "schedule")
public class WorkTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(nullable = false, length = 20, columnDefinition = "DATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startBooking;

    @Column(nullable = false, length = 20, columnDefinition = "DATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endBooking;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private UserEntity worker;

    @ManyToOne
    @JoinColumn(name = "work_id", nullable = false)
    private WorkType work;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "report_id")
    private ReportEntity report;


}