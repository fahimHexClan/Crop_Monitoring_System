package lk.ijse.Crop_monitoring_system.Dto;

import lk.ijse.Crop_monitoring_system.Dto.Status.MonitoringLogStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDTO implements MonitoringLogStatus {
    private Long id;
    private Date logDate;
    private String logDetails;
    private String observedImage;
    private List<FieldDTO> fields; // Fields associated with the log
    private List<CropDTO> crops; // Crops associated with the log
    private List<StaffDTO> staff;
}
