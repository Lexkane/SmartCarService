package ua.ita.smartcarservice.entity.sales;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "TradeIn")
public class TradeIn {

    public TradeIn() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = false)
    private String vinNewCar;

    @Column(length = 100, nullable = false, unique = false)
    private String vinUsedCar;

    @Column(length = 100, nullable = false, unique = false)
    private Long idUser;

    @Column(length = 100, nullable = false, unique = false)
    private Long idDealer;

    @Column(length = 100, nullable = false, unique = false)
    private String isactive;


    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = true,unique = false)
     private DealerEntity dealer;


    public TradeIn(String vinNewCar, String vinUsedCar, Long idUser, Long idDealer, String isactive) {
        this.vinNewCar = vinNewCar;
        this.vinUsedCar = vinUsedCar;
        this.idUser = idUser;
        this.idDealer = idDealer;
        this.isactive = isactive;

    }

    public TradeIn(String vinNewCar, String vinUsedCar, Long idUser, Long idDealer, String isactive, DealerEntity dealer) {
        this.vinNewCar = vinNewCar;
        this.vinUsedCar = vinUsedCar;
        this.idUser = idUser;
        this.idDealer = idDealer;
        this.isactive = isactive;
        this.dealer = dealer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdDealer() {
        return idDealer;
    }

    public void setIdDealer(Long idDealer) {
        this.idDealer = idDealer;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public DealerEntity getDealer() {
        return dealer;
    }

    public void setDealer(DealerEntity dealer) {
        this.dealer = dealer;
    }
}

