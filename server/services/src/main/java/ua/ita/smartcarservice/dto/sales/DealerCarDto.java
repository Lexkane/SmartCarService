package ua.ita.smartcarservice.dto.sales;

import java.util.Date;


public class DealerCarDto {

    private Long id;
    private String brand;
    private String model;
    private String graduationYyear;
    private String number;
    private Double price;
    private String vin;
    private Date endEguarantee;
    private String usernameDealer;

    public DealerCarDto(String brand, String model, String graduationYyear, String number, Double price, String vin, Date endGguarantee, String usernameDealer) {
        this.brand = brand;
        this.model = model;
        this.graduationYyear = graduationYyear;
        this.number = number;
        this.price = price;
        this.vin = vin;
        this.endEguarantee = endGguarantee;
        this.usernameDealer = usernameDealer;
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

    public String getGraduationYyear() {
        return graduationYyear;
    }

    public void setGraduationYyear(String graduationYyear) {
        this.graduationYyear = graduationYyear;
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

    public Date getEndEguarantee() {
        return endEguarantee;
    }

    public void setEndEguarantee(Date endEguarantee) {
        this.endEguarantee = endEguarantee;
    }

    public String getUsernameDealer() {
        return usernameDealer;
    }

    public void setUsernameDealer(String usernameDealer) {
        this.usernameDealer = usernameDealer;
    }
}