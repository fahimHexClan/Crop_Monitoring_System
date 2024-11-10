package lk.ijse.Crop_monitoring_system.Dto;

import lk.ijse.Crop_monitoring_system.Entity.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String email;
    private String password;
    private Role role;
}
