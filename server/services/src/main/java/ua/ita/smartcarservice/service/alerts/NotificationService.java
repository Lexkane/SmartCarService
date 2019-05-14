package ua.ita.smartcarservice.service.alerts;

import java.util.List;
import ua.ita.smartcarservice.dto.alerts.NotificationsDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.entity.alerts.Notifications;

public interface NotificationService {

	void saveNotification(NotificationsDto notificationDto);

	void saveAllNotifications(List<NotificationsDto> notificationsList);

	void addNewNotification(RecordDto recordDto);

	List<NotificationsDto> getAllNotificationsForUser(Long userId);

	NotificationsDto getNotification(Long id);

	void disableNotification(NotificationsDto notificationDto);

	void deleteNotificationById(Long id);

}
