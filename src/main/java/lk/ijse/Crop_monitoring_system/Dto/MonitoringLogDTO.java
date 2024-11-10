package lk.ijse.Crop_monitoring_system.Dto;

import jakarta.persistence.*;
import lk.ijse.Crop_monitoring_system.Entity.CropEntity;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
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
    private Date logDate;
    private String logDetails;
    private byte[] observedImage;
    private List<FieldDTO> fields; // Fields associated with the log
    private List<CropDTO> crops; // Crops associated with the log
    private List<StaffDTO> staff;
}
