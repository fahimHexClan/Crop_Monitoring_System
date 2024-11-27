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
    @OneToMany(mappedBy = "log")
    private List<CropEntity> crops;
    @OneToMany(mappedBy = "log")
    private List<StaffEntity> staff;
    @OneToMany(mappedBy = "log")
    private List<FieldEntity> fields;


}
