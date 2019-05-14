package ua.ita.smartcarservice.service.impl.alerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.alerts.AlertsDto;
import ua.ita.smartcarservice.entity.alerts.Alerts;
import ua.ita.smartcarservice.repository.alerts.AlertsRepository;
import ua.ita.smartcarservice.service.alerts.AlertsService;

@Service
public class AlertsImpl implements AlertsService {

	@Autowired
	private AlertsRepository alertsRepository;

	@Override
	public AlertsDto getAlert(String code) {
		return entityToDto(alertsRepository.getAlert(code));
	}

	private AlertsDto entityToDto(Alerts entity) {
		return new AlertsDto(entity.getAlertCode(),
				entity.getDescription(),
				entity.getSuggestion(),
				entity.getAlertType(),
				entity.getWorkType());
	}
}
