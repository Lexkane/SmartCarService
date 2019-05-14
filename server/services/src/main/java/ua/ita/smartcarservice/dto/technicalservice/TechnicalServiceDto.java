package ua.ita.smartcarservice.dto.technicalservice;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;

import java.util.List;

/**
 * DTO for TechnicalService Entity.
 */
@Data
public class TechnicalServiceDto {

    private Long stoId;

    private String name;

    private String address;

    private Double rating;

    private UserEntity technicalManager;

    private List<UserEntity> workers;
}
