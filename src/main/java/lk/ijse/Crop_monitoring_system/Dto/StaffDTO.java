package lk.ijse.Crop_monitoring_system.Dto;

import lk.ijse.Crop_monitoring_system.util.Designation;
import lk.ijse.Crop_monitoring_system.util.Gender;
import lk.ijse.Crop_monitoring_system.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO {
    private Long id;
    private String staffCode;
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

    private List<FieldDTO> fields; // List of associated FieldDTOs
    private List<VehicleDto> vehicles;
}
