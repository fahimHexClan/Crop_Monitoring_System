package lk.ijse.Crop_monitoring_system.util;

import lk.ijse.Crop_monitoring_system.Dto.*;
import lk.ijse.Crop_monitoring_system.Entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;


    public FieldEntity toFieldEntity(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }

    public UserEntity toUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public StaffEntity toStaffEntity(StaffDTO staffDto) {
        return modelMapper.map(staffDto, StaffEntity.class);
    }

    public FieldDTO toFieldDTO(FieldEntity field) {
        return modelMapper.map(field, FieldDTO.class);
    }

    public UserDTO toUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public List<FieldDTO> asFieldDTOList(List<FieldEntity> fieldList) {
        return modelMapper.map(fieldList, new TypeToken<List<FieldDTO>>() {
        }.getType());
    }

    public List<UserDTO> asUserDTOList(List<UserEntity> userEntities) {
        return modelMapper.map(userEntities, new TypeToken<List<UserDTO>>() {
        }.getType());
    }


    public List<CropDTO> asNoteDTOList(List<CropEntity> crops) {
        return modelMapper.map(crops, new TypeToken<List<CropDTO>>() {
        }.getType());
    }

    public CropEntity toCropEntity(CropDTO cropDto) {
        FieldEntity fieldEntity = new FieldEntity();
        fieldEntity.setFieldId(cropDto.getFieldId());

        MonitoringLogEntity monitoringLogEntity = new MonitoringLogEntity();
        monitoringLogEntity.setId(cropDto.getLogId());

        CropEntity cropEntity = modelMapper.map(cropDto, CropEntity.class);

        cropEntity.setField(fieldEntity);
        cropEntity.setLog(monitoringLogEntity);

        return cropEntity;
    }

    public CropDTO toCropDTO(CropEntity crop) {
        return modelMapper.map(crop, CropDTO.class);
    }


    public StaffDTO toStaffDTO(StaffEntity staff) {
        return modelMapper.map(staff, StaffDTO.class);
    }

    public List<StaffDTO> asStaffDTOList(List<StaffEntity> staffList) {
        return modelMapper.map(staffList, new TypeToken<List<StaffDTO>>() {
        }.getType());
    }

    public MonitoringLogEntity toMoniteringLogEntity(MonitoringLogDTO moniteringLogDto) {
        return modelMapper.map(moniteringLogDto, MonitoringLogEntity.class);
    }

    public List<MonitoringLogDTO> asLogsDTOList(List<MonitoringLogEntity> all) {
        return modelMapper.map(all, new TypeToken<List<MonitoringLogDTO>>() {
        }.getType());

    }

    public MonitoringLogDTO toMoniteringLogDto(MonitoringLogEntity selectedlog) {
        return modelMapper.map(selectedlog, MonitoringLogDTO.class);

    }
}
