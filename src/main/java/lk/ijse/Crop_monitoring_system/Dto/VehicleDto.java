package lk.ijse.Crop_monitoring_system.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDto {
    private Long id; // Unique identifier for the vehicle
    private String vehicleCode; // Unique code for the vehicle
    private String licensePlateNumber; // Vehicle's license plate number
    private String category; // Vehicle category (e.g., Truck, Tractor)
    private String fuelType; // Fuel type (e.g., Diesel, Petrol)
    private String status; // Status of the vehicle (e.g., Available, Under Maintenance)
    private String remarks; // Additional information about the vehicle

    private List<StaffDTO> staff; // List of associated staff (drivers, operators)
}
