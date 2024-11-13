package lk.ijse.Crop_monitoring_system.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO {
    private Long id;
    private String commonName;
    private String scientificName;
    private String  cropImage;
    private String category;
    private String season;
    private FieldDTO field;
}
