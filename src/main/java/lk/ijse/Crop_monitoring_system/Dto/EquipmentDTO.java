package lk.ijse.Crop_monitoring_system.Dto;

import jakarta.persistence.*;
import lk.ijse.Crop_monitoring_system.Entity.Enums.EquipmentStatus;
import lk.ijse.Crop_monitoring_system.Entity.Enums.EquipmentType;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO {
    private Long id;
    private String name;
    private EquipmentType type;
    private EquipmentStatus status;
    private StaffEntity assignedStaff;
    private FieldEntity assignedField;
}
