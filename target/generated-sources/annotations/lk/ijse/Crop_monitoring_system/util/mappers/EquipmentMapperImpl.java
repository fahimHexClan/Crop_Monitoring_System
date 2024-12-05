package lk.ijse.Crop_monitoring_system.util.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import lk.ijse.Crop_monitoring_system.Dto.EquipmentDTO;
import lk.ijse.Crop_monitoring_system.Entity.EquipmentEntity;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-05T23:52:33+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class EquipmentMapperImpl implements EquipmentMapper {

    @Override
    public EquipmentDTO entityToDTO(EquipmentEntity equipmentEntity) {
        if ( equipmentEntity == null ) {
            return null;
        }

        EquipmentDTO equipmentDTO = new EquipmentDTO();

        equipmentDTO.setAssignedStaff( equipmentEntityAssignedStaffId( equipmentEntity ) );
        equipmentDTO.setAssignedField( equipmentEntityAssignedFieldFieldId( equipmentEntity ) );
        equipmentDTO.setId( equipmentEntity.getId() );
        equipmentDTO.setName( equipmentEntity.getName() );
        equipmentDTO.setType( equipmentEntity.getType() );
        equipmentDTO.setStatus( equipmentEntity.getStatus() );

        return equipmentDTO;
    }

    @Override
    public List<EquipmentDTO> entityListToDTOList(List<EquipmentEntity> equipmentEntityList) {
        if ( equipmentEntityList == null ) {
            return null;
        }

        List<EquipmentDTO> list = new ArrayList<EquipmentDTO>( equipmentEntityList.size() );
        for ( EquipmentEntity equipmentEntity : equipmentEntityList ) {
            list.add( entityToDTO( equipmentEntity ) );
        }

        return list;
    }

    private Long equipmentEntityAssignedStaffId(EquipmentEntity equipmentEntity) {
        if ( equipmentEntity == null ) {
            return null;
        }
        StaffEntity assignedStaff = equipmentEntity.getAssignedStaff();
        if ( assignedStaff == null ) {
            return null;
        }
        Long id = assignedStaff.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long equipmentEntityAssignedFieldFieldId(EquipmentEntity equipmentEntity) {
        if ( equipmentEntity == null ) {
            return null;
        }
        FieldEntity assignedField = equipmentEntity.getAssignedField();
        if ( assignedField == null ) {
            return null;
        }
        Long fieldId = assignedField.getFieldId();
        if ( fieldId == null ) {
            return null;
        }
        return fieldId;
    }
}
