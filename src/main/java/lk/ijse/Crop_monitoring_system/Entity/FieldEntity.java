package lk.ijse.Crop_monitoring_system.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Field")
@ToString
public class FieldEntity implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Field_id")
    private Long FieldId;

    @Column(name = "field_Name", nullable = false)
    private String fieldName;

    @Column(name = "field_location")
    private Point location;//Point Data type useful for applications that need to work with geographic data such as mapping, location tracking, and spatial analysis.

    @Column(name = "field_extentSize", nullable = false)
    private Double extentSize;

    @Column(name = "fieldImage1",columnDefinition = "LONGTEXT")
    private String fieldImage1;

    @Column(name = "fieldImage2",columnDefinition = "LONGTEXT")
    private String fieldImage2;


    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CropEntity> crops; // Crops associated with this field

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "field_staff_mapping",
            joinColumns = @JoinColumn(name = "field_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    @JsonManagedReference
    private List<StaffEntity> staff= new ArrayList<>(); // Staff assigned to this field


}


