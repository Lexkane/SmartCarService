package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

@Data
public class ReportDto {

    private long carId;

    private String startTime;

    private String endTime;

    private int requiredTime;

    private int price;

}
