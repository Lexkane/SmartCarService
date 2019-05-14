package ua.ita.smartcarservice.dto.sales;

public class CreateTraidInDto {

    private String dealerEdr;

    private String username;

    private String vinNewCar;

    private String vinUsedCar;

    public CreateTraidInDto(String dealerEdr, String username, String vinNewCar, String vinUsedCar) {
        this.dealerEdr = dealerEdr;
        this.username = username;
        this.vinNewCar = vinNewCar;
        this.vinUsedCar = vinUsedCar;
    }

    public String getDealerEdr() {
        return dealerEdr;
    }

    public void setDealerEdr(String dealerEdr) {
        this.dealerEdr = dealerEdr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVinNewCar() {
        return vinNewCar;
    }

    public void setVinNewCar(String vinNewCar) {
        this.vinNewCar = vinNewCar;
    }

    public String getVinUsedCar() {
        return vinUsedCar;
    }

    public void setVinUsedCar(String vinUsedCar) {
        this.vinUsedCar = vinUsedCar;
    }
}

