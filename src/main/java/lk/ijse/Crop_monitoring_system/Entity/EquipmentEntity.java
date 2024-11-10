package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
import lk.ijse.Crop_monitoring_system.util.EquipmentStatus;
import lk.ijse.Crop_monitoring_system.util.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Equipment")
public class EquipmentEntity {
    @Id
    @Column(name = "Equipment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Equipment_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "Equipment_Type", nullable = false)
    private EquipmentType type;

    @Enumerated(EnumType.STRING)//When persisting these enum values into a database, JPA needs to know how to store and retrieve them
    @Column(name = "Equipment_Status", nullable = false)
    private EquipmentStatus status;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity assignedStaff;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity assignedField;
}


