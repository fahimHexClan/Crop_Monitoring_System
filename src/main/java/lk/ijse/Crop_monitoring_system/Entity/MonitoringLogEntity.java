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
public class MonitoringLogEntity implements SuperEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Log_id")
    private Long id;

    @Temporal(TemporalType.DATE)
    //@Temporal annotation is correct database column type is used for storing date or time information
    @Column(name = "Moniter_logDate")
    private Date logDate;

    @Column(name = "Moniter_logDetails")
    private String logDetails;

    @Column(name = "Moniter_LogImage",columnDefinition = "LONGTEXT")
    private String observedImage;
    //byte=Used to store  image ,pdf,word documents,audio ,video in databse

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "log_field_mapping",
            joinColumns = @JoinColumn(name = "log_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id")
    )
    private List<FieldEntity> fields; // Fields associated with the log

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "log_crop_mapping",
            joinColumns = @JoinColumn(name = "log_id"),
            inverseJoinColumns = @JoinColumn(name = "crop_id")
    )
    private List<CropEntity> crops; // Crops associated with the log

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "log_staff_mapping",
            joinColumns = @JoinColumn(name = "log_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private List<StaffEntity> staff; // Staff members associated with the log
}