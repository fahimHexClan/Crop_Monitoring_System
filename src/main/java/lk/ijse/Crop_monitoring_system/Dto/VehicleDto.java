package lk.ijse.Crop_monitoring_system.Dto;

import jakarta.persistence.*;
import lk.ijse.Crop_monitoring_system.Entity.Enums.FuelType;
import lk.ijse.Crop_monitoring_system.Entity.Enums.VehicleCategory;
import lk.ijse.Crop_monitoring_system.Entity.Enums.VehicleStatus;
import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDto {
    private Long id;
    private String licensePlateNumber;
    private VehicleCategory category;
    private FuelType fuelType;
    private VehicleStatus status;
    private StaffEntity staff;
    private String remarks;
}
