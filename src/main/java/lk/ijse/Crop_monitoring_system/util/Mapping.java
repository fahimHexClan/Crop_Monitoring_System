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
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void getidCropconfigureMappings() {
        // Custom configuration for mapping CropEntity to CropDTO
        modelMapper.typeMap(CropEntity.class, CropDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getField().getFieldId(), CropDTO::setFieldId);  // Custom mapping for fieldId
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



    public CropEntity toCropEntity(CropDTO cropDto) {
        return modelMapper.map(cropDto, CropEntity.class);
    }
    public CropDTO toCropDTO(CropEntity crop) {
        return modelMapper.map(crop, CropDTO.class);
    }


    public List<CropDTO> asCropDTOList(List<CropEntity> crops) {
        return modelMapper.map(crops, new TypeToken<List<CropDTO>>() {}.getType());

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
