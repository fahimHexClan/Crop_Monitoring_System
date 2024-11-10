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

    @Column(nullable = false)
    private String commonName; // Common name of the crop

    @Column(nullable = false)
    private String scientificName; // Scientific name of the crop

    @Lob
    private byte[]  cropImage; // Image of the crop

    @Column(nullable = false)
    private String category; // Category of the crop (e.g., Fruit, Vegetable)

    @Column(nullable = false)
    private String season; // Growing season of the crop

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private FieldEntity field; // Associated field
}


