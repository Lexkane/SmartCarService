package ua.ita.smartcarservice.controller.alerts;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.alerts.AlertsDto;
import ua.ita.smartcarservice.dto.alerts.NotificationsDto;
import ua.ita.smartcarservice.service.CarService;
import ua.ita.smartcarservice.service.alerts.AlertsService;
import ua.ita.smartcarservice.service.alerts.NotificationService;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/api")
public class AlertsController {

	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	@Autowired
	private AlertsService alertsService;

	@Autowired
	private CarService carService;

	@Autowired
	private NotificationService notificationsService;

	@Autowired
	private SimpMessagingTemplate template;

	/* Method for handling fault code received from car */
	@PostMapping("/alertCode")
	public void handleFaultCode(@RequestParam(value="vinNumber") String vinNumber,
								@RequestParam(value="code") String code) {
		try {
			AlertsDto faultCode = alertsService.getAlert(code);
			CarDto car = carService.findByVin(vinNumber);
			NotificationsDto toSave = new NotificationsDto(faultCode, car);
			notificationsService.saveNotification(toSave);
			template.convertAndSend("/notifications-list", toSave);
		} catch (Exception e) {
			logger.error("Error! Cannot add notification while handling fault code received from car: " + e.getMessage());
		}
	}
}
