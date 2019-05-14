package ua.ita.smartcarservice.entity.files;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;

@Entity
@Data
@Table(name = "car_norms")
public class CarNorms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = false)
    private String brand;

    @Column(length = 20, nullable = false, unique = false)
    private String model;

    @Column(length = 4, nullable = false, unique = false)
    @JsonFormat(pattern = "yyyy")
    private String graduation_year;

    @Column(length = 10, nullable = true, unique = false)
    @ColumnDefault("null")
    private Double norm_of_tire_pressure;

    @Column(length = 10, nullable = true, unique = false)
    @ColumnDefault("null")
    private Double volume_of_brake_fluid;

    @Column(length = 10, nullable = true, unique = false)
    @ColumnDefault("null")
    private Double volume_of_oil;

    @Column(length = 10, nullable = true, unique = false)
    @ColumnDefault("null")
    private Double volume_of_coolant;

}
