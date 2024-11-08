package lk.ijse.Crop_monitoring_system.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveFieldRequestDto {
    private String FieldName;
    private String Field;
    private Double ExtentSizeOfTheField;
}
