package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Log_id", length = 45)
    private Long id;

    @Column(unique = true, nullable = false)
    private String logCode; // Unique identifier for the log

    @Temporal(TemporalType.DATE)
    private Date logDate; // Date of the log entry

    @Lob
    private String logDetails; // Detailed description of the log

    @Lob
    private byte[] observedImage; // Image associated with the log

    @ManyToMany
    @JoinTable(
            name = "log_field_mapping",
            joinColumns = @JoinColumn(name = "log_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id")
    )
    private List<FieldEntity> fields; // Fields associated with the log

    @ManyToMany
    @JoinTable(
            name = "log_crop_mapping",
            joinColumns = @JoinColumn(name = "log_id"),
            inverseJoinColumns = @JoinColumn(name = "crop_id")
    )
    private List<CropEntity> crops; // Crops associated with the log

    @ManyToMany
    @JoinTable(
            name = "log_staff_mapping",
            joinColumns = @JoinColumn(name = "log_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private List<StaffEntity> staff; // Staff members associated with the log
}