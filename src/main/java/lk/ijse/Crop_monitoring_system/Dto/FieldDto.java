package lk.ijse.Crop_monitoring_system.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FieldDto {
    private String FieldId;

    private String FieldName;
    private String Field;
    private Double ExtentSizeOfTheField;
   /* private List<> crops;
    private Staff<> staff;
    private String FieldImage1;
    private String FieldImage2;*/

}
