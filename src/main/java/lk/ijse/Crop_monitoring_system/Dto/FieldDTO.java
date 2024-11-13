package lk.ijse.Crop_monitoring_system.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FieldDTO {
    private Long FieldId;
    private String fieldName;
    private String location;//String to Dto and Point Data type entity
    private Double extentSize;
    private String fieldImage1;
    private String fieldImage2;
    private List<CropDTO> crops; // Crops associated with this field
    private List<StaffDTO> staff; // Staff assigned to t

}
