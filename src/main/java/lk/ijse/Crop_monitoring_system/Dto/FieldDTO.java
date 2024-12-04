package lk.ijse.Crop_monitoring_system.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.ijse.Crop_monitoring_system.Dto.Status.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@AllArgsConstructor

@NoArgsConstructor
@Setter
@Getter
public class FieldDTO implements FieldStatus {
    private Long fieldId;
    private String fieldName;
    private Point fieldLocation;  // Instead of String
    private Double extentSize;
    private String fieldImage1;
    private String fieldImage2;
    private List<CropDTO> crops;// Crops associated with this field
    @JsonIgnore
    private List<StaffDTO> staff; // Staff assigned to t
    private Long logId;
}