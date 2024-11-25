package lk.ijse.Crop_monitoring_system.Dto;

import lk.ijse.Crop_monitoring_system.Dto.Status.CropStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements CropStatus {
    private Long cropCode;
    private String commonName;
    private String scientificName;
    private String  cropImage;
    private String category;
    private String season;
    private Long fieldId;
    private Long logId;
}
