package lk.ijse.Crop_monitoring_system.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO {
    private Long id;
    private String equipmentCode;
    private String name;
    private String type;
    private String status;
    private StaffDTO assignedStaff; // DTO for the assigned staff member
    private FieldDTO assignedField; // DTO for the assigned field
}
