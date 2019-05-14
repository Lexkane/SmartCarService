package ua.ita.smartcarservice.entity.alerts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "alerts")
public class Alerts {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column (length = 35, name = "alert_code", nullable = false, unique = true)
	private String alertCode;
	
	@Column (length = 550, name = "description", nullable = false)
	private String description;

	@Column (length = 2050, name = "suggestion")
	private String suggestion;

	@Column (length = 100, name = "alert_type", nullable = false)
	private String alertType;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_type_id")
	private WorkType workType;

	public Alerts(String alertCode, String description, String alertType, WorkType workType) {
		this.alertCode = alertCode;
		this.description = description;
		this.alertType = alertType;
		this.workType = workType;
	}

	/* Constructor for warning lights that do not requires service help */
	public Alerts(String alertCode, String description, String suggestion, String alertType) {
		this.alertCode = alertCode;
		this.description = description;
		this.suggestion = suggestion;
		this.alertType = alertType;
	}
}
