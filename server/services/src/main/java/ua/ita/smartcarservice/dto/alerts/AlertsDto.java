package ua.ita.smartcarservice.dto.alerts;

import lombok.Data;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;

@Data
public class AlertsDto{

	private long id;
	private String alertCode;
	private String description;
	private String alertType;
	private String suggestion;
	private WorkType workType;

	public AlertsDto(long id, String alertCode, String description, String alertType, WorkType workType) {
		this.id = id;
		this.alertCode = alertCode;
		this.description = description;
		this.alertType = alertType;
		this.workType = workType;
	}

	/* Constructor for warning lights that requires service help */
	public AlertsDto(String alertCode, String description, String suggestion, String alertType, WorkType workType) {
		this.alertCode = alertCode;
		this.description = description;
		this.suggestion = suggestion;
		this.alertType = alertType;
		this.workType = workType;
	}

	/* Constructor for warning lights that do not requires service help */
	public AlertsDto(String alertCode, String description, String suggestion, String alertType) {
		this.alertCode = alertCode;
		this.description = description;
		this.suggestion = suggestion;
		this.alertType = alertType;
	}
}
