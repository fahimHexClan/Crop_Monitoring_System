package lk.ijse.Crop_monitoring_system.Service;

import lk.ijse.Crop_monitoring_system.Dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    String addVehicle(VehicleDto vehicleDto);

    String updateVehicle(VehicleDto vehicleDto);

    List<VehicleDto> getAllVehicles();
}
