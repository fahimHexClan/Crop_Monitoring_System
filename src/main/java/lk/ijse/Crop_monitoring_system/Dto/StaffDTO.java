package lk.ijse.Crop_monitoring_system.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.ijse.Crop_monitoring_system.Dto.Status.StaffStatus;
import lk.ijse.Crop_monitoring_system.Entity.Enums.Designation;
import lk.ijse.Crop_monitoring_system.Entity.Enums.Gender;
import lk.ijse.Crop_monitoring_system.Entity.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements StaffStatus {
    private Long id;
    private String firstName;
    private String lastName;
    private Designation designation;
    private Gender gender;
    private Date joinedDate;
    private Date dob;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNo;
    private String email;
    private Role role;
    @JsonIgnore
    private List<FieldDTO> fields;
    private List<VehicleDto> vehicles;
    private List<EquipmentDTO> equipmentDTOS;
    private Long logId;
}
