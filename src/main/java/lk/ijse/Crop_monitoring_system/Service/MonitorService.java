package lk.ijse.Crop_monitoring_system.Service;

import lk.ijse.Crop_monitoring_system.Dto.MonitoringLogDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.MonitoringLogStatus;

import java.util.List;

public interface MonitorService {
    List<MonitoringLogDTO> getAllLogs();

    void saveLogs(MonitoringLogDTO moniteringLogDto);

    MonitoringLogStatus getLogs(Long logID);

    void deleteLogs(Long logID);

    void updatedLogs(Long logID, MonitoringLogDTO updatedLogsDTO);
}
