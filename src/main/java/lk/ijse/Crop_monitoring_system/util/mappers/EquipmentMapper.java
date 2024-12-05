package lk.ijse.Crop_monitoring_system.util.mappers;

import lk.ijse.Crop_monitoring_system.Dto.EquipmentDTO;
import lk.ijse.Crop_monitoring_system.Entity.EquipmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    @Mapping(source = "assignedStaff.id", target = "assignedStaff")
    @Mapping(source = "assignedField.fieldId", target = "assignedField")
    EquipmentDTO entityToDTO(EquipmentEntity equipmentEntity);

    List<EquipmentDTO> entityListToDTOList(List<EquipmentEntity> equipmentEntityList);
}
