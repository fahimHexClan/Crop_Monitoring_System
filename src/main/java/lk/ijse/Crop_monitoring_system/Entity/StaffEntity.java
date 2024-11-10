package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
import lk.ijse.Crop_monitoring_system.util.Gender;
import lk.ijse.Crop_monitoring_system.util.Designation;
import lk.ijse.Crop_monitoring_system.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Staff")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName; // First name of the staff member

    @Column(nullable = false)
    private String lastName; // Last name of the staff member

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Designation designation; // Designation of the staff member

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender; // Gender of the staff member

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date joinedDate; // Date the staff member joined

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dob; // Date of birth of the staff member

    private String addressLine1; // Address line 1
    private String addressLine2; // Address line 2
    private String addressLine3; // Address line 3
    private String addressLine4; // Address line 4
    private String addressLine5; // Address line 5

    @Column(nullable = false)
    private String contactNo; // Contact number

    @Column(nullable = false, unique = true)
    private String email; // Email address

    @Enumerated(EnumType.STRING)
    private Role role; // Role of the staff member

    @ManyToMany(mappedBy = "staff")
    private List<FieldEntity> fields; // Fields associated with the staff member

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VehicleEntity> vehicles; //  Vehicles assigned to the staff member
}


