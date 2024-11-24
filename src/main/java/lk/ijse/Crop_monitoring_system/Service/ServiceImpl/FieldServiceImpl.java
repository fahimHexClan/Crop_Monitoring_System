package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.FieldStatus;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Repository.FieldRepo;
import lk.ijse.Crop_monitoring_system.Repository.StaffRepo;
import lk.ijse.Crop_monitoring_system.Service.FieldServise;
import lk.ijse.Crop_monitoring_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldServise {
    @Autowired
    private FieldRepo fieldRepo;
    @Autowired
    private Mapping mapping;
    @Autowired
    private StaffRepo staffRepo;


    @Override
    @Transactional
    public void SaveField(FieldDTO fieldDto) {
        FieldEntity fieldEntity = mapping.toFieldEntity(fieldDto);

        // Handle Many-to-Many relationship with Staff
        if (fieldDto.getStaff() != null && !fieldDto.getStaff().isEmpty()) {
            for (StaffDTO staffDto : fieldDto.getStaff()) {
                StaffEntity staffEntity = staffRepo.findById(staffDto.getStaffCode())
                        .orElseThrow(() -> new DataPersistException("Staff not found with ID: " + staffDto.getStaffCode()));

                // Step 4: Use the helper method to maintain both sides of the relationship
                fieldEntity.addStaff(staffEntity);
            }
        }

        // Save Field entity and ensure it is attached to the persistence context
        fieldRepo.saveAndFlush(fieldEntity);
    }

    @Override
    public void updateField(Long fieldCode, FieldDTO updatedFieldDTO) {
        Optional<FieldEntity> findField = fieldRepo.findById(fieldCode);
        if (!findField.isPresent()) {
            throw new DataPersistException("Field not found: " + fieldCode);
        } else {
            FieldEntity field = findField.get();
            field.setLocation(updatedFieldDTO.getFieldLocation());
            field.setFieldName(updatedFieldDTO.getFieldName());
            field.setExtentSize(updatedFieldDTO.getExtentSize());
            field.setFieldImage1(updatedFieldDTO.getFieldImage1());
            field.setFieldImage2(updatedFieldDTO.getFieldImage2());



            // Save updated Field entity
            fieldRepo.save(field);
        }
    }
    @Override
    public void deletefield(Long fieldCode) {
        // Check if the Field exists before deletion
        Optional<FieldEntity> foundField = fieldRepo.findById(fieldCode);
        if (!foundField.isPresent()) {
            throw new DataPersistException("Field not found");
        } else {
            fieldRepo.deleteById(fieldCode);
        }

    }

    @Override
    public FieldStatus getField(Long fieldCode) {
        // Fetch Field by ID and map to FieldDto
        if (fieldRepo.existsById(fieldCode)) {
            FieldEntity selectedField = fieldRepo.getReferenceById(fieldCode);
            return mapping.toFieldDTO(selectedField);
        } else {
           /* return new SelectedErrorStatus(2, "Selected Field not found");*/
            throw new DataPersistException("Field not found");

        }

    }

    @Override
    public List<FieldDTO> getAllField() {
        List<FieldEntity> fields = fieldRepo.findAll();
        if (fields.isEmpty()) {
            throw new DataPersistException("No fields found");
        }
        return mapping.asFieldDTOList(fields);
    }

}

