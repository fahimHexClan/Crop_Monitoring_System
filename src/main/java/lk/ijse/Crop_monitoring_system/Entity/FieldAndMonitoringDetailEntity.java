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
@Table(name = "FieldAndMonitoringDetailEntity")
public class FieldAndMonitoringDetailEntity {
    @Id
    @Column(name = "Field_And_Monitoring_id" , length = 45)

    private String FieldAndMonitoringDetailId;
}
