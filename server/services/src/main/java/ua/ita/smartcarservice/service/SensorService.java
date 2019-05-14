package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;

public interface SensorService {

    void addRecord(RecordDto recordDto);

    ChartDto findDataByPeriod(DateForChartDto dateForChartDto);

    ChartDto findLastRecordValue(DateForChartDto dateForChartDto);

    RecordDto findRecordBeforeDate(RecordDto recordDto);

}
