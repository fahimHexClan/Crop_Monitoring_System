package lk.ijse.Crop_monitoring_system.util.Mappers;

import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface FieldMapper {

    FieldEntity RequesDtoToEntity(FieldDTO fieldDto);
}
