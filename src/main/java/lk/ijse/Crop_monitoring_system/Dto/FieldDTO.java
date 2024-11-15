package lk.ijse.Crop_monitoring_system.Dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lk.ijse.Crop_monitoring_system.Dto.Status.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data

public class FieldDTO implements FieldStatus {
    private Long fieldId;
    private String fieldName;
    private LocationDTO location;  // Instead of String
    private Double extentSize;
    private String fieldImage1;
    private String fieldImage2;
    private List<CropDTO> crops; // Crops associated with this field
    private List<StaffDTO> staff; // Staff assigned to t

    public FieldDTO() {

    }

    @JsonCreator
    public FieldDTO(
            @JsonProperty("fieldId") Long fieldId,
            @JsonProperty("fieldName") String fieldName,
            @JsonProperty("location") LocationDTO location,
            @JsonProperty("extentSize") Double extentSize,
            @JsonProperty("fieldImage1") String fieldImage1,
            @JsonProperty("fieldImage2") String fieldImage2,
            @JsonProperty("crops") List<CropDTO> crops,
            @JsonProperty("staff") List<StaffDTO> staff) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.location = location;
        this.extentSize = extentSize;
        this.fieldImage1 = fieldImage1;
        this.fieldImage2 = fieldImage2;
        this.crops = crops;
        this.staff = staff;
    }
}