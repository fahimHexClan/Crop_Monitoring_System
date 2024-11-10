package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
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

    @Column(unique = true, nullable = false)
    private String vehicleCode; // Unique identifier for the vehicle

    private String licensePlateNumber; // License plate number

    private String category; // Category of the vehicle (e.g., Truck, Tractor)

    private String fuelType; // Fuel type (e.g., Diesel, Petrol)

    private String status; // Status of the vehicle (e.g., Active, Under Maintenance)

    @ManyToMany
    @JoinTable(
            name = "vehicle_staff_mapping",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private List<StaffEntity> staff; // Staff members associated with the vehicle

    private String remarks; // Additional remarks about the vehicle
}

