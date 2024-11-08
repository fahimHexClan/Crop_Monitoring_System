package lk.ijse.Crop_monitoring_system.util.Mappers;

import javax.annotation.processing.Generated;
import lk.ijse.Crop_monitoring_system.Dto.FieldDto;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-09T03:38:15+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class FieldMapperImpl implements FieldMapper {

    @Override
    public FieldEntity RequesDtoToEntity(FieldDto fieldDto) {
        if ( fieldDto == null ) {
            return null;
        }

        FieldEntity fieldEntity = new FieldEntity();

        fieldEntity.setFieldId( fieldDto.getFieldId() );
        fieldEntity.setFieldName( fieldDto.getFieldName() );
        fieldEntity.setField( fieldDto.getField() );
        fieldEntity.setExtentSizeOfTheField( fieldDto.getExtentSizeOfTheField() );

        return fieldEntity;
    }
}
