package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.MonitoringLogDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.MonitoringLogStatus;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.MonitoringLogEntity;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Repository.MonitorRepo;
import lk.ijse.Crop_monitoring_system.Service.MonitorService;
import lk.ijse.Crop_monitoring_system.util.Mapping;
import lk.ijse.Crop_monitoring_system.util.SelectedErrorStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MonitorServiceImpl implements MonitorService {
    @Autowired
    private MonitorRepo moniteringLogDao;
    @Autowired
    private Mapping logsMapping;

    @Override
    public void saveLogs(MonitoringLogDTO moniteringLogDto) {
        moniteringLogDto.getId();
        MonitoringLogEntity savedLogs =
                moniteringLogDao.save(logsMapping.toMoniteringLogEntity(moniteringLogDto));
        if(savedLogs == null){
            throw new DataPersistException("Logs not saved");
        }
    }

    @Override
    public MonitoringLogStatus getLogs(Long logID) {
        if(moniteringLogDao.existsById(logID)){
            var selectedlog = moniteringLogDao.getReferenceById(logID);
            return logsMapping.toMoniteringLogDto(selectedlog);
        }else {
            return new SelectedErrorStatus(2,"Selected Logs not found");
        }
    }

    @Override
    public void deleteLogs(Long logID) {
        Optional<MonitoringLogEntity> foundLogs = moniteringLogDao.findById(logID);
        if (!foundLogs.isPresent()) {
            throw new DataPersistException("Logs not found");
        }else {
            moniteringLogDao.deleteById(logID);
        }
    }

    @Override
    public void updatedLogs(Long logID, MonitoringLogDTO updatedLogsDTO) {
        Optional<MonitoringLogEntity> foundLogs = moniteringLogDao.findById(logID);
        if (!foundLogs.isPresent()) {
            throw new DataPersistException("Logs not found");
        }else {

            foundLogs.get().setLogDate(updatedLogsDTO.getLogDate());
            foundLogs.get().setLogDetails(updatedLogsDTO.getLogDetails());
            foundLogs.get().setObservedImage(updatedLogsDTO.getObservedImage());



    }
    }

    @Override
    public List<Long> getAllLogIds() {
        List<MonitoringLogEntity> monitoringLogEntities = moniteringLogDao.findAll();
        List<Long> logIds = new ArrayList<>();
        for (MonitoringLogEntity monitoringLogEntity : monitoringLogEntities) {
            logIds.add(monitoringLogEntity.getId()); // Assuming FieldEntity has a getFieldId method
        }
        return logIds;
    }


    @Override
    public List<MonitoringLogDTO> getAllLogs() {
        return logsMapping.asLogsDTOList(moniteringLogDao.findAll());
    }

}
