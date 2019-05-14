package ua.ita.smartcarservice.controller.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.service.SensorService;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/chart")
public class ChartController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public ResponseEntity<ChartDto> findDataByPeriod(@RequestParam Map<String,String> parametersMap) {
        return getResponse(sensorService.findDataByPeriod(new DateForChartDto(parametersMap)));
    }

    @GetMapping(value = "/last")
    public ResponseEntity<ChartDto> findLastRecordValue(@RequestParam Map<String,String> parametersMap) {
        return getResponse(sensorService.findLastRecordValue(new DateForChartDto(parametersMap)));
    }

    private ResponseEntity<ChartDto> getResponse(ChartDto chartDto) {
        return (chartDto.getData().size() == 0) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(chartDto, HttpStatus.OK);
    }
}
