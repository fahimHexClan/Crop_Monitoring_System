package lk.ijse.Crop_monitoring_system.Dto;

import lk.ijse.Crop_monitoring_system.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private Long id;
    private String email;
    private Role role; // User's role
}
