package lk.ijse.Crop_monitoring_system.Dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lk.ijse.Crop_monitoring_system.Dto.Status.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FieldDTO implements FieldStatus {
    private Long fieldId;
    private String fieldName;
    private String location;//String to Dto and Point Data type entity
    private Double extentSize;
    private String fieldImage1;
    private String fieldImage2;
    private List<CropDTO> crops; // Crops associated with this field
    private List<StaffDTO> staff; // Staff assigned to t



    // Constructor with parameters
    @JsonCreator
    public FieldDTO(@JsonProperty("fieldId") Long fieldId,
                    @JsonProperty("fieldName") String fieldName,
                    @JsonProperty("location") String location,
                    @JsonProperty("extentSize") Double extentSize,
                    @JsonProperty("fieldImage1") String fieldImage1,
                    @JsonProperty("fieldImage2") String fieldImage2) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.location = location;
        this.extentSize = extentSize;
        this.fieldImage1 = fieldImage1;
        this.fieldImage2 = fieldImage2;
    }

}
