package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
import lk.ijse.Crop_monitoring_system.Entity.Enums.FuelType;
import lk.ijse.Crop_monitoring_system.Entity.Enums.VehicleCategory;
import lk.ijse.Crop_monitoring_system.Entity.Enums.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Vehicle")
public class VehicleEntity implements SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Vehicle_id")
    private Long id;

    @Column(name = "vehicle_licensePlateNumber" , length = 45)
    private String licensePlateNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_Category", nullable = false)
    private VehicleCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_Type", nullable = false)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_status", nullable = false)
    private VehicleStatus status;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;

    @Column(name = "vehicle_remarks")
    private String remarks;
}

