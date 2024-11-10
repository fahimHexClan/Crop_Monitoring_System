package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Field")
public class FieldEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Field_id" , length = 45)
    private Long FieldId;

    @Column(unique = true, nullable = false)
    private String fieldCode; // Unique field code

    private String fieldName;  // Descriptive name for the field

    @Column(nullable = false)
    private Point location;   // GPS or address

    @Column(nullable = false)
    private Double extentSize; // Extent size in square meters

    @Lob
    private String fieldImage1; // Image of the field (large data)

    @Lob
    private String fieldImage2; // Optional secondary field image

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

