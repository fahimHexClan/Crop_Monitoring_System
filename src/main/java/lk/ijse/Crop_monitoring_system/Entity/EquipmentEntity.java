package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
import lk.ijse.Crop_monitoring_system.Entity.Enums.EqStatus;
import lk.ijse.Crop_monitoring_system.Entity.Enums.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Equipment")
public class EquipmentEntity implements SuperEntity{
    @Id
    @Column(name = "Equipment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Equipment_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "Equipment_Type", nullable = false)
    private EquipmentType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "Equipment_Status", nullable = false)
    private EqStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id",nullable = false)
    private StaffEntity assignedStaff;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "field_id")
    private FieldEntity assignedField;
}


