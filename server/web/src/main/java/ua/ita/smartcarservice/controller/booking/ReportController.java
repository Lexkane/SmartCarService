package ua.ita.smartcarservice.controller.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.booking.ProgresDto;
import ua.ita.smartcarservice.dto.booking.ReportDto;
import ua.ita.smartcarservice.dto.booking.ReportExtendedDto;
import ua.ita.smartcarservice.entity.booking.ReportEntity;
import ua.ita.smartcarservice.service.booking.BookingService;
import ua.ita.smartcarservice.service.booking.ReportService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public void saveReport(@RequestBody ReportDto reportDto) {
        ReportEntity reportEntity = reportService.addReport(reportDto);
        bookingService.updateReportsId(reportDto, reportEntity);
    }

    @GetMapping("/progress/{id}")
    public ResponseEntity<List<ProgresDto>> findAllReportsByUserId(@PathVariable Long id) {
        List<ProgresDto> reports = reportService.findAllReportsByUserId(id);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }



    @GetMapping("/pdf/{reportId}")
    public ResponseEntity<byte[]> formPdf(@PathVariable Long reportId) {
        byte[] pdf = reportService.formExtendedReport(reportId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "report.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<ReportEntity> findReportById(@PathVariable Long reportId) {
        return new ResponseEntity<>(reportService.findReportById(reportId), HttpStatus.OK);
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<ReportExtendedDto>> findReportsByCarId(@PathVariable Long carId) {
        return new ResponseEntity<>(reportService.findAllReportsByCarId(carId), HttpStatus.OK);
    }

}
