package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
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

@Entity
@Table(name = "Staff")
public class StaffEntity implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Staff_id")
    private Long id;

    @Column(name = "Staff_first_Name", nullable = false)
    private String firstName;

    @Column(name = "Staff_last_Name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "Staff_designation", nullable = false)
    private Designation designation;

    @Enumerated(EnumType.STRING)
    @Column(name = "staff_gender", nullable = false)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "staff_joined_Date", nullable = false)
    private Date joinedDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "staff_dob", nullable = false)
    private Date dob;

    @Column(name = "staff_addressLine1")
    private String addressLine1;

    @Column(name = "staff_addressLine2")
    private String addressLine2;

    @Column(name = "staff_addressLine3")
    private String addressLine3;

    @Column(name = "staff_addressLine4")
    private String addressLine4;

    @Column(name = "staff_addressLine5")
    private String addressLine5;

    @Column(name = "staff_contactNo", nullable = false)
    private String contactNo;

    @Column(name = "staff_email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "staff_role")
    private Role role;

    @ManyToMany(mappedBy = "staff")
    private List<FieldEntity> fields;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VehicleEntity> vehicles; //  Vehicles assigned to the staff member

    @OneToMany(mappedBy = "assignedStaff",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipmentEntity> equipments;
}


