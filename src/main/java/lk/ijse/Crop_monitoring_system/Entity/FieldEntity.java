package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.awt.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Field")
public class FieldEntity implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Field_id")
    private Long FieldId;

    @Column(name = "field_Name", nullable = false)
    private String fieldName;

    @Column(name = "field_location",columnDefinition = "POINT", nullable = false)
    private Point location;//Point Data type useful for applications that need to work with geographic data such as mapping, location tracking, and spatial analysis.

    @Column(name = "field_extentSize", nullable = false)
    private Double extentSize;

    @Column(name = "fieldImage1",columnDefinition = "LONGTEXT")
    private String fieldImage1;

    @Column(name = "fieldImage2",columnDefinition = "LONGTEXT")
    private String fieldImage2;


    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CropEntity> crops; // Crops associated with this field

    @ManyToMany
    @JoinTable(
            name = "field_staff_mapping",
            joinColumns = @JoinColumn(name = "field_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private List<StaffEntity> staff; // Staff assigned to this field
}

