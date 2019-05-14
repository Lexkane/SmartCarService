package ua.ita.smartcarservice.service.alerts;

import java.util.Date;
import java.util.List;

import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.alerts.VehicleInspectionDto;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.alerts.VehicleInspection;

public interface VehicleInspectionService {

	void saveVehicleInspection (VehicleInspectionDto vehicleInspectionDto);

	List<VehicleInspectionDto> getCarsForYearlyInspection ();

	VehicleInspectionDto getVehicleInspection(long id);

	List<CarDto> getCarsForVehicleInspectionByMileage();

	void createVehicleInspection(java.sql.Date dateOfInspection, Integer mileageOfCar, String vin);
}
