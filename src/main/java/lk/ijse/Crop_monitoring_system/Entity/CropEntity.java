package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Crop")
public class CropEntity {
    @Id
    @Column(name = "Crop_id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Crop_commonName", nullable = false)
    private String commonName;

    @Column(name = "Crop_scientificName", nullable = false)
    private String scientificName;

    @Lob//This is particularly useful when you need to store large amounts of data,
    @Column(name = "Crop_image")
    private byte[]  cropImage;

    @Column(name = "Crop_category", nullable = false)// using null false mean-should always have a value
    private String category;

    @Column(name = "season", nullable = false)
    private String season;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private FieldEntity field;
}


