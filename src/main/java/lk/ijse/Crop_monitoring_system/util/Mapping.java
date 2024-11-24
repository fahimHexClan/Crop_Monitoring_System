package lk.ijse.Crop_monitoring_system.util;

import jakarta.annotation.PostConstruct;
import lk.ijse.Crop_monitoring_system.Dto.CropDTO;
import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Entity.CropEntity;
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


}
