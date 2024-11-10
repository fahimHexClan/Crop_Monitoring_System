package lk.ijse.Crop_monitoring_system.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDTO {
    private Long id;
    private String logCode;
    private Date logDate;
    private String logDetails;
    private String observedImage;

    private List<FieldDTO> fields; // List of associated FieldDTOs
    private List<CropDTO> crops; // List of associated CropDTOs
    private List<StaffDTO> staff; // List of associated StaffDTOs
}
