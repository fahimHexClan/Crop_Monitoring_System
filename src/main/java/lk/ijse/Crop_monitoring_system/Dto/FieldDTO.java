package lk.ijse.Crop_monitoring_system.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FieldDTO {
    private Long id;
    private String fieldCode;
    private String fieldName;
    private String location;
    private Double extentSize;
    private String fieldImage1;
    private String fieldImage2;

    private java.util.List<CropDTO> crops; // List of CropDTOs
    private List<StaffDTO> staff; // List of StaffDTOs

}
