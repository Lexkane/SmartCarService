package ua.ita.smartcarservice.entity.alerts;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "notifications")
public class Notifications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column (length = 1050, name = "message", nullable = false)
	private String message;

	@Column (length = 1050, name = "suggestion")
	private String suggestion;

	@Column (name = "notification_time", columnDefinition = "DATETIME")
	private Timestamp notificationTime;

	@Column (length = 100, name = "type", nullable = false)
	private String type;

	@Column (name = "is_visible")
	@ColumnDefault("true")
	private Boolean isVisible;

	@Column (name = "car_id")
	private Long carId;

	@Column (name = "user_id")
	private Long userId;

	@Column (name = "work_type_name")
	private String workTypeName;

	public Notifications(String message, String suggestion, Timestamp notificationTime, String type, Boolean isVisible,
						 Long carId, Long userId, String workTypeName) {
		this.message = message;
		this.suggestion = suggestion;
		this.notificationTime = notificationTime;
		this.type = type;
		this.isVisible = isVisible;
		this.carId = carId;
		this.userId = userId;
		this.workTypeName = workTypeName;
	}

	public Notifications(Long id, String message, String suggestion, Timestamp notificationTime, String type, Boolean isVisible,
						 Long carId, Long userId, String workTypeName) {
		this.id = id;
		this.message = message;
		this.suggestion = suggestion;
		this.notificationTime = notificationTime;
		this.type = type;
		this.isVisible = isVisible;
		this.carId = carId;
		this.userId = userId;
		this.workTypeName = workTypeName;
	}
}
