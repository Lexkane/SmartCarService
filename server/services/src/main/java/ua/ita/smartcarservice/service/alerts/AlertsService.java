package ua.ita.smartcarservice.service.alerts;

import ua.ita.smartcarservice.dto.alerts.AlertsDto;

public interface AlertsService {

	AlertsDto getAlert (String alertCode);

}
