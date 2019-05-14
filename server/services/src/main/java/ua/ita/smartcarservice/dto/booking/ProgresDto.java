package ua.ita.smartcarservice.dto.booking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProgresDto {

 private String model;

 private String brand;

 private LocalDateTime startTime;

 private LocalDateTime endTime;


 private List<Long> workersId;


}
