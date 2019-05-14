package ua.ita.smartcarservice.dto;

import lombok.Data;

@Data
public class NewCarDTO {

    private String brand;
    private String model;
    private String graduation_year;
    private String number;
    private String vin;

}
