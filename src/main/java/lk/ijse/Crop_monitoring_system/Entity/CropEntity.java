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
@Table(name = "Crop")
public class CropEntity {
    @Id
    @Column(name = "field_id" , length = 45)
    private String Cropcode;

    private String CropCommonName;
    private String CropScientificName;
    private String CropImage;
    private String Category;
    private String CropSession;
    private String Field;

}
