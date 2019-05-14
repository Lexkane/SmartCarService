package ua.ita.smartcarservice.service.impl.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.ProgresDto;
import ua.ita.smartcarservice.dto.booking.ReportDto;
import ua.ita.smartcarservice.dto.booking.ReportExtendedDto;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.booking.ReportEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.booking.ReportRepository;
import ua.ita.smartcarservice.repository.technicalservice.UserTechnicalServiceRepository;
import ua.ita.smartcarservice.service.booking.ReportService;
import ua.ita.smartcarservice.service.booking.WorkTimeService;
import ua.ita.smartcarservice.service.pdf.PdfCreator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserTechnicalServiceRepository userTechnicalServiceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkTimeService workTimeService;


    @Override
    public ReportEntity addReport(ReportDto reportDto) {
        return reportRepository.save(repordDtoToEntity(reportDto));
    }

    private ReportEntity repordDtoToEntity(ReportDto reportDto) {
        ReportEntity entity = new ReportEntity();

        entity.setCar(carRepository.getCarById(reportDto.getCarId()));
        entity.setTechnicalService(userTechnicalServiceRepository
                .findTechnicalServiceByCarId(reportDto.getCarId())
                .getTechnicalServiceId());

        entity.setStartTime(parseDateToLocal(reportDto.getStartTime()));
        entity.setEndTime(parseDateToLocal(reportDto.getEndTime()));
        entity.setRequiredTime(reportDto.getRequiredTime());
        entity.setPrice(reportDto.getPrice());
        return entity;
    }


    private LocalDateTime parseDateToLocal(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(s, formatter);
    }

    @Override
    public ReportEntity findReportById(long reportId) {
        return reportRepository.findById(reportId).get();
    }

    @Override
    public List<ProgresDto> findAllReportsByUserId(long userId) {
        return reportRepository.findAllReportsByUserId(userId).stream()
                .map(this::transformEntityToProgresDto).collect(Collectors.toList());
    }

    @Override
    public List<ReportExtendedDto> findAllReportsByCarId(long carId) {
        return reportRepository.findAllReportsByCarId(carId).stream()
                .map(this::reportEntityToExtendedDto).collect(Collectors.toList());
    }

    @Override
    public byte[] formExtendedReport(long reportId) {
        return new PdfCreator().getByteArrayWithPdfReport(
                reportEntityToExtendedDto(reportRepository.findById(reportId).get()));
    }

    private ReportExtendedDto reportEntityToExtendedDto(ReportEntity report) {
        ReportExtendedDto dto = new ReportExtendedDto();
        dto.setReportId(report.getReportId());

        long carId = report.getCar().getId();
        dto.setUserFullName(userRepository.findUserEntityByCarId(carId).getFullName());

        Car car = carRepository.getCarById(carId);
        dto.setCarBrand(car.getBrand());
        dto.setCarModel(car.getModel());
        dto.setCarNumber(car.getNumber());

        dto.setStartTime(report.getStartTime());
        dto.setEndTime(report.getEndTime());
        dto.setRequiredTime(report.getRequiredTime());
        dto.setPrice(report.getPrice());

        TechnicalServiceEntity serviceEntity = userTechnicalServiceRepository
                .findTechnicalServiceByCarId(carId).getTechnicalServiceId();

        dto.setTechnicalServiceName(serviceEntity.getName());
        dto.setTechnicalServiceAddress(serviceEntity.getAddress());
        dto.setWorkerTasks(workTimeService.findWorkerTasksByReportId(report.getReportId()));
        return dto;
    }

    private ProgresDto transformEntityToProgresDto(ReportEntity entity){

        ProgresDto dto = new ProgresDto();
        dto.setBrand(entity.getCar().getBrand());
        dto.setModel(entity.getCar().getModel());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        List<Long> workers = new ArrayList<Long>();
        entity.getWorkTimes().forEach(worker -> {
            workers.add(worker.getWorker().getId());
        });
        dto.setWorkersId(workers);
        return dto;
    }

}


