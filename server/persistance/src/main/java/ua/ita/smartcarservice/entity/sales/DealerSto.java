package ua.ita.smartcarservice.entity.sales;

import lombok.Data;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DealerSto")
public class DealerSto {

    public DealerSto() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = true,unique = false)
    private DealerEntity dealer;


    @ManyToOne
    @JoinColumn(name = "technical_service_id", nullable = false)
    private TechnicalServiceEntity technicalService;


    public DealerSto(DealerEntity dealer, TechnicalServiceEntity technicalService) {
        this.dealer = dealer;
        this.technicalService = technicalService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DealerEntity getDealer() {
        return dealer;
    }

    public void setDealer(DealerEntity dealer) {
        this.dealer = dealer;
    }

    public TechnicalServiceEntity getTechnicalService() {
        return technicalService;
    }

    public void setTechnicalService(TechnicalServiceEntity technicalService) {
        this.technicalService = technicalService;
    }
}
