package ua.ita.smartcarservice.dto.sales;

public class ApplyToDealerDto {

    private  String dealerEdr;
    private Long stoId;

    public ApplyToDealerDto() {
    }


    public ApplyToDealerDto(String dealerEdr, Long stoId) {
        this.dealerEdr = dealerEdr;
        this.stoId = stoId;
    }

    public String getDealerEdr() {
        return dealerEdr;
    }

    public void setDealerEdr(String dealerEdr) {
        this.dealerEdr = dealerEdr;
    }

    public Long getStoId() {
        return stoId;
    }

    public void setStoId(Long stoId) {
        this.stoId = stoId;
    }

    @Override
    public String toString() {
        return "ApplyToDealerDto{" +
                "dealerEdr='" + dealerEdr + '\'' +
                ", stoId=" + stoId +
                '}';
    }
}
