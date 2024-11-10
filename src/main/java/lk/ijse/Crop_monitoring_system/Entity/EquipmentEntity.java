package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
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
    @Column(name = "Equipment_id" , length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String equipmentCode; // Unique identifier for the equipment

    @Column(nullable = false)
    private String name; // Name of the equipment

    private String type; // Type or category of the equipment

    private String status; // Current status (e.g., Available, In Use, Under Maintenance)

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity assignedStaff; // Staff member responsible for the equipment

    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldEntity assignedField; // Field where the equipment is deployed
}


