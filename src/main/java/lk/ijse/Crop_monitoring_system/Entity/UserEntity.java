package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
import lk.ijse.Crop_monitoring_system.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "User")
public class UserEntity {

    @Id
    @Column(name = "Email_id" , length = 45)
    private String email;

    @Column(name = "usr_password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "usr_role")
    private Role role;

}
