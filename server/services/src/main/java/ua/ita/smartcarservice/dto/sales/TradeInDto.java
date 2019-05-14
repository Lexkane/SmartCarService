package ua.ita.smartcarservice.dto.sales;

import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.UserDto;

public class TradeInDto {

    private Long id;

    private String userFullName;

    private String userEmail;

    private String userPhone;

    private String newCarModel;

    private String newCarBrand;

    private Double newCarPrice;

    private String usedCarModel;

    private String usedCarBrand;


    public TradeInDto(Long id, String userFullName, String userEmail, String userPhone, String newCarModel, String newCarBrand, Double newCarPrice, String usedCarModel, String usedCarBrand) {
        this.id = id;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.newCarModel = newCarModel;
        this.newCarBrand = newCarBrand;
        this.newCarPrice = newCarPrice;
        this.usedCarModel = usedCarModel;
        this.usedCarBrand = usedCarBrand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getNewCarModel() {
        return newCarModel;
    }

    public void setNewCarModel(String newCarModel) {
        this.newCarModel = newCarModel;
    }

    public String getNewCarBrand() {
        return newCarBrand;
    }

    public void setNewCarBrand(String newCarBrand) {
        this.newCarBrand = newCarBrand;
    }

    public Double getNewCarPrice() {
        return newCarPrice;
    }

    public void setNewCarPrice(Double newCarPrice) {
        this.newCarPrice = newCarPrice;
    }

    public String getUsedCarModel() {
        return usedCarModel;
    }

    public void setUsedCarModel(String usedCarModel) {
        this.usedCarModel = usedCarModel;
    }

    public String getUsedCarBrand() {
        return usedCarBrand;
    }

    public void setUsedCarBrand(String usedCarBrand) {
        this.usedCarBrand = usedCarBrand;
    }
}