package lk.ijse.Crop_monitoring_system.Dto;
import lk.ijse.Crop_monitoring_system.Dto.Status.EquipmentStatus;
import lk.ijse.Crop_monitoring_system.Entity.Enums.EqStatus;
import lk.ijse.Crop_monitoring_system.Entity.Enums.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements EquipmentStatus {
    private Long id;
    private String name;
    private EquipmentType type;
    private EqStatus status;
    private  Long assignedStaff;
    private Long assignedField;
}
