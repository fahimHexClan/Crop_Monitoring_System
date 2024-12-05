package lk.ijse.Crop_monitoring_system.util;

import lk.ijse.Crop_monitoring_system.Dto.Status.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements FieldStatus, CropStatus, StaffStatus, MonitoringLogStatus, VehicleStatus, EquipmentStatus,UserStatus {
        private int statusCode;
        private String statusMessage;
    }
