package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
import lk.ijse.Crop_monitoring_system.util.FuelType;
import lk.ijse.Crop_monitoring_system.util.VehicleCategory;
import lk.ijse.Crop_monitoring_system.util.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Vehicle")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Vehicle_id" , length = 45)
    private Long id;

    private String licensePlateNumber; // License plate number

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleCategory category; // Category of the vehicle (e.g., Truck, Tractor)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType; // Fuel type (e.g., Diesel, Petrol)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleStatus status; // Status of the vehicle (e.g., Active, Under Maintenance)

    @ManyToMany
    @JoinTable(
            name = "vehicle_staff_mapping_details",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private List<StaffEntity> staff; // Staff members associated with the vehicle

    private String remarks;
}

