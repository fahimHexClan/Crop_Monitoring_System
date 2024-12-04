package lk.ijse.Crop_monitoring_system.Dto;

import lk.ijse.Crop_monitoring_system.Dto.Status.VehicleStatus;
import lk.ijse.Crop_monitoring_system.Entity.Enums.FuelType;
import lk.ijse.Crop_monitoring_system.Entity.Enums.VehicleCategory;
import lk.ijse.Crop_monitoring_system.Entity.Enums.VehStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDto implements VehicleStatus {
    private Long id;
    private String licensePlateNumber;
    private VehicleCategory category;
    private FuelType fuelType;
    private VehStatus status;
    private Long staffId;
    private String remarks;
}
