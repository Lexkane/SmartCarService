package ua.ita.smartcarservice.service.booking;



import ua.ita.smartcarservice.dto.booking.ProgresDto;
import ua.ita.smartcarservice.dto.booking.ReportDto;
import ua.ita.smartcarservice.dto.booking.ReportExtendedDto;
import ua.ita.smartcarservice.entity.booking.ReportEntity;

import java.util.List;

public interface ReportService {

    ReportEntity addReport(ReportDto reportDto);

    ReportEntity findReportById(long reportId);

    List<ReportExtendedDto> findAllReportsByCarId(long reportId);

    List<ProgresDto> findAllReportsByUserId(long userId);

    byte[] formExtendedReport(long reportId);

}
