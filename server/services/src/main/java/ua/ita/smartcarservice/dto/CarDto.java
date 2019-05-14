package ua.ita.smartcarservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.alerts.VehicleInspection;
import ua.ita.smartcarservice.entity.sales.DealerEntity;

import java.util.Date;
import java.util.Set;

@Data
public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private String graduation_year;
    private String number;
    private Double price;
    private String vin;
    private Date endGuarantee;
    private DealerEntity dealerEntity;
    private UserEntity carOwner;

    @JsonIgnore
    private Set<VehicleInspection> vehicleInspections;

    public CarDto() {
    }

    //for register  car by dealer
    public CarDto(Long id, String brand, String model, String graduation_year, String number, Double price, String vin, Date endGuarantee, DealerEntity dealer, UserEntity carOwner, Set<VehicleInspection> vehicleInspections) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.graduation_year = graduation_year;
        this.number = number;
        this.price = price;
        this.vin = vin;
        this.endGuarantee = endGuarantee;
        this.dealerEntity = dealer;
        this.carOwner = carOwner;
        this.vehicleInspections = vehicleInspections;
    }

    //for register by user
    public CarDto(String brand, String model, String graduation_year, String number, String vin, UserEntity carOwner) {
        this.brand = brand;
        this.model = model;
        this.graduation_year = graduation_year;
        this.number = number;
        this.vin = vin;
        this.carOwner = carOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGraduation_year() {
        return graduation_year;
    }

    public void setGraduation_year(String graduation_year) {
        this.graduation_year = graduation_year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getEndGuarantee() {
        return endGuarantee;
    }

    public void setEndGuarantee(Date end_guarantee) {
        this.endGuarantee = end_guarantee;
    }

    public DealerEntity getDealerEntity() {
        return dealerEntity;
    }

    public void setDealerEntity(DealerEntity dealerEntity) {
        this.dealerEntity = dealerEntity;
    }

    public UserEntity getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(UserEntity carOwner) {
        this.carOwner = carOwner;
    }

    public Set<VehicleInspection> getVehicleInspections() {
        return vehicleInspections;
    }

    public void setVehicleInspections(Set<VehicleInspection> vehicleInspections) {
        this.vehicleInspections = vehicleInspections;
    }
}
