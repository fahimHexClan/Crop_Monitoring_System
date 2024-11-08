package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Vehicle")
public class VehicleEntity {
    @Id
    @Column(name = "Vehicle_id" , length = 45)

    private String VehicleCode;
    private String LicensePlateNumber;
    private String VehicleCategory;
    private String FuelType;
    private String Status;
    private String AllocatedStaffMemberDetails;
    private String Remarks;
}
