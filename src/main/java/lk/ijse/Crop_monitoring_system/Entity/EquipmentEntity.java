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
@Table(name = "Equipment")
public class EquipmentEntity {
    @Id
    @Column(name = "Equipment_id" , length = 45)

    private String EquipmentId;
    private String Name;
    private String Type;
    private String Status;
    private String AssignedStaffDetails;
    private String AssignedFieldDetails;

}
