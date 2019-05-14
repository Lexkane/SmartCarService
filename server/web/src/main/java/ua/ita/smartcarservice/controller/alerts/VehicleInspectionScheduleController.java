package ua.ita.smartcarservice.controller.alerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.ita.smartcarservice.service.alerts.AlertsService;
import ua.ita.smartcarservice.service.alerts.NotificationService;
import ua.ita.smartcarservice.service.alerts.VehicleInspectionService;

import java.sql.Date;


@RestController
public class VehicleInspectionScheduleController {

	@Autowired
	private VehicleInspectionService vehicleInspectionService;

	@PostMapping("/api/inspection")
	public void createVehicleInspection (@RequestParam(value = "dateOfInspection") Date dateOfInspection,
										 @RequestParam(value = "mileageOfCar") Integer mileageOfCar,
										 @RequestParam(value = "vin") String vin){
		vehicleInspectionService.createVehicleInspection(dateOfInspection, mileageOfCar, vin);
	}
}
