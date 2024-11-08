package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "MonitoringLog")
public class MonitoringLogEntity {
    @Id
    @Column(name = "Log_id" , length = 45)
    private String Logcode;
    private Date LogDate;
    private String LogDetails;
    private String ObservedImage;
    private String Field;
    private String Crop;
    private String Staff;
}
