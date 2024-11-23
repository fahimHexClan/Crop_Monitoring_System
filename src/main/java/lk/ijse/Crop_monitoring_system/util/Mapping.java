package lk.ijse.Crop_monitoring_system.util;

import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.FieldStatus;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;


    // Convert FieldDTO to FieldEntity
    public FieldEntity toFieldEntity(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }

    public FieldDTO toFieldDTO(FieldEntity field) {
        return modelMapper.map(field, FieldDTO.class);
    }


    public List<FieldDTO> asFieldDTOList(List<FieldEntity> fieldList) {
        return modelMapper.map(fieldList, new TypeToken<List<FieldDTO>>() {}.getType());
    }

}
