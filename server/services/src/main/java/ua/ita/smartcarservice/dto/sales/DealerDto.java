package ua.ita.smartcarservice.dto.sales;

public class DealerDto {

    private String dealerName;

    private String dealerAddress;

    private String dealerEdr;

    private String dealerEmail;


    public DealerDto(String dealerName, String dealerAddress, String dealerEdr, String dealerEmail) {
        this.dealerName = dealerName;
        this.dealerAddress = dealerAddress;
        this.dealerEdr = dealerEdr;
        this.dealerEmail = dealerEmail;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerAddress() {
        return dealerAddress;
    }

    public void setDealerAddress(String dealerAddress) {
        this.dealerAddress = dealerAddress;
    }

    public String getDealerEdr() {
        return dealerEdr;
    }

    public void setDealerEdr(String dealerEdr) {
        this.dealerEdr = dealerEdr;
    }

    public String getDealerEmail() {
        return dealerEmail;
    }

    public void setDealerEmail(String dealerEmail) {
        this.dealerEmail = dealerEmail;
    }

    @Override
    public String toString() {
        return "DealerDto{" +
                "dealerName='" + dealerName + '\'' +
                ", dealerAddress='" + dealerAddress + '\'' +
                ", dealerEdr='" + dealerEdr + '\'' +
                ", dealerEmail='" + dealerEmail + '\'' +
                '}';
    }
}
