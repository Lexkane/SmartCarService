package ua.ita.smartcarservice.service.impl.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.sales.ApplyToDealerDto;
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.sales.DealerSto;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.repository.sales.DealerStoRepository;
import ua.ita.smartcarservice.repository.sales.DealerEntityRepository;
import ua.ita.smartcarservice.repository.technicalservice.TechnicalServiceRepository;
import ua.ita.smartcarservice.service.sales.ApplyService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private DealerEntityRepository dealerRepository;

    @Autowired
    TechnicalServiceRepository technicalServiceRepository;

    @Autowired
    DealerStoRepository dealerStoRepository;

    @Override
    public void createApplyToDealer(ApplyToDealerDto applyToDealerDto) {
        if(dealerStoRepository.findByDealerAndAndTechnicalService(dealerRepository.findByDealerEdr(applyToDealerDto.getDealerEdr()),technicalServiceRepository.getByTechnicalServiceId(applyToDealerDto.getStoId()))==null){
            dealerStoRepository.save(new DealerSto(dealerRepository.findByDealerEdr(applyToDealerDto.getDealerEdr()),technicalServiceRepository.getByTechnicalServiceId(applyToDealerDto.getStoId())));
        }

    }

    @Override
    public void deleteApplyToDealer(ApplyToDealerDto applyToDealerDto) {
dealerStoRepository.delete(dealerStoRepository.findByDealerAndAndTechnicalService(dealerRepository.findByUserEntityUsername(applyToDealerDto.getDealerEdr()),technicalServiceRepository.getByTechnicalServiceId(applyToDealerDto.getStoId())));
    }


    @Override
    public void applyToDealer(ApplyToDealerDto applyToDealerDto) {

        DealerEntity dealerEntity=dealerRepository.findByUserEntityUsername(applyToDealerDto.getDealerEdr());
        TechnicalServiceEntity technicalServiceEntity=technicalServiceRepository.getByTechnicalServiceId(applyToDealerDto.getStoId());
        technicalServiceEntity.setDealer(dealerEntity);
        technicalServiceRepository.save(technicalServiceEntity);
        deleteApplyToDealer(applyToDealerDto);
    }

    public TechnicalServiceDto convertToDto(TechnicalServiceEntity technicalServiceEntity) {
        TechnicalServiceDto dto = new TechnicalServiceDto();
        dto.setStoId(technicalServiceEntity.getTechnicalServiceId());
        dto.setName(technicalServiceEntity.getName());
        dto.setAddress(technicalServiceEntity.getAddress());
        return dto;
    }

    @Override
    public List<TechnicalServiceDto> technicalServiceDtos(String username) {
List<TechnicalServiceDto>technicalServiceDtos=new ArrayList<>();
        List<DealerSto>applies= dealerStoRepository.findAllByDealer_UserEntityUsername(username);

        for (DealerSto dealerSto :applies){
            technicalServiceDtos.add(convertToDto(technicalServiceRepository.getByTechnicalServiceId(dealerSto.getTechnicalService().getTechnicalServiceId())));
        }
        return technicalServiceDtos;
    }


}
