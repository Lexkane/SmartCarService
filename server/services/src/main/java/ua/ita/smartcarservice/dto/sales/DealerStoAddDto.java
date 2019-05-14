package ua.ita.smartcarservice.dto.sales;

public class DealerStoAddDto {

    private String nameSto;

    private String addressSto;

    public DealerStoAddDto(String nameSto, String addressSto) {
        this.nameSto = nameSto;
        this.addressSto = addressSto;
    }

    public String getNameSto() {
        return nameSto;
    }

    public void setNameSto(String nameSto) {
        this.nameSto = nameSto;
    }

    public String getAddressSto() {
        return addressSto;
    }

    public void setAddressSto(String addressSto) {
        this.addressSto = addressSto;
    }
}
