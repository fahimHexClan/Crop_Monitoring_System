package lk.ijse.Crop_monitoring_system.util;

import jakarta.annotation.PostConstruct;
import lk.ijse.Crop_monitoring_system.Dto.CropDTO;
import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Dto.MonitoringLogDTO;
import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.MonitoringLogStatus;
import lk.ijse.Crop_monitoring_system.Dto.Status.StaffStatus;
import lk.ijse.Crop_monitoring_system.Entity.CropEntity;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.MonitoringLogEntity;
import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        modelMapper.addMappings(new PropertyMap<CropDTO, CropEntity>() {
            @Override
            protected void configure() {
                map().setId(null); // Explicitly set id to null to avoid conflict
            }
        });
    }


    // Convert FieldDTO to FieldEntity
    public FieldEntity toFieldEntity(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }

 public StaffEntity toStaffEntity(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }

    public FieldDTO toFieldDTO(FieldEntity field) {
        return modelMapper.map(field, FieldDTO.class);
    }

    public List<FieldDTO> asFieldDTOList(List<FieldEntity> fieldList) {
        return modelMapper.map(fieldList, new TypeToken<List<FieldDTO>>() {}.getType());
    }


    public List<CropDTO> asNoteDTOList(List<CropEntity> crops) {
        return modelMapper.map(crops, new TypeToken<List<CropDTO>>() {}.getType());
    }

    public CropEntity toCropEntity(CropDTO cropDto) {
        // First, we manually map the complex objects (FieldEntity and MonitoringLogEntity)
        FieldEntity fieldEntity = new FieldEntity();
        fieldEntity.setFieldId(cropDto.getFieldId());  // Map fieldId to FieldEntity

        MonitoringLogEntity monitoringLogEntity = new MonitoringLogEntity();
        monitoringLogEntity.setId(cropDto.getLogId());  // Map logId to MonitoringLogEntity

        // Now map the simple properties normally, but exclude 'id' to avoid conflict
        CropEntity cropEntity = modelMapper.map(cropDto, CropEntity.class);

        // Manually set the complex properties (FieldEntity and MonitoringLogEntity)
        cropEntity.setField(fieldEntity);
        cropEntity.setLog(monitoringLogEntity);

        return cropEntity;}
        public CropDTO toCropDTO (CropEntity crop){
            return modelMapper.map(crop, CropDTO.class);
        }



    public StaffDTO toStaffDTO(StaffEntity selectedStaff) {
        return modelMapper.map(selectedStaff, StaffDTO.class);

    }

    public List<StaffDTO> asStaffDTOList(List<StaffEntity> all) {
        return modelMapper.map(all, new TypeToken<List<StaffDTO>>() {}.getType());

    }

    public MonitoringLogEntity toMoniteringLogEntity(MonitoringLogDTO moniteringLogDto) {
        return modelMapper.map(moniteringLogDto, MonitoringLogEntity.class);
    }

    public List<MonitoringLogDTO> asLogsDTOList(List<MonitoringLogEntity> all) {
        return modelMapper.map(all, new TypeToken<List<MonitoringLogDTO>>() {}.getType());

    }

    public MonitoringLogDTO toMoniteringLogDto(MonitoringLogEntity selectedlog) {
        return modelMapper.map(selectedlog, MonitoringLogDTO.class);

    }
}
