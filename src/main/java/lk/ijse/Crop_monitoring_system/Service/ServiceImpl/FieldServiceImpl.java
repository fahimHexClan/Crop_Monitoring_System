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
    public void SaveField(FieldDTO fieldDto) {
        // Map FieldDto to Field entity
        FieldEntity fieldEntity = mapping.toFieldEntity(fieldDto);

        // Handle Many-to-Many relationship with Staffs
        List<StaffEntity> staffEntities = new ArrayList<>();
        if (fieldDto.getStaff() != null) {
            for (StaffDTO staffDto : fieldDto.getStaff()) {
                StaffEntity staff = staffRepo.findById(staffDto.getId()).orElseThrow(() -> new DataPersistException("Staff not found with ID: " + staffDto.getId()));
                staffEntities.add(staff);
            }
        }
        fieldEntity.setStaff(staffEntities);

        // Save Field entity
        FieldEntity savedField = fieldRepo.save(fieldEntity);
        if (savedField == null) {
            throw new DataPersistException("Field not saved");
        }
    }

    @Override
    public void updateField(Long fieldCode, FieldDTO updatedFieldDTO) {
        // Fetch Field by ID and update its details
        Optional<FieldEntity> findField = fieldRepo.findById(fieldCode);
        if (!findField.isPresent()) {
            throw new DataPersistException("Field not found"+fieldCode);
        } else {
            FieldEntity field = findField.get();
            field.setLocation(updatedFieldDTO.getFieldLocation());
            field.setFieldName(updatedFieldDTO.getFieldName());
            field.setExtentSize(updatedFieldDTO.getExtentSize());
            field.setFieldImage1(updatedFieldDTO.getFieldImage1());
            field.setFieldImage2(updatedFieldDTO.getFieldImage2());

            // Update Many-to-Many relationship with Staffs
            List<StaffEntity> staffs = new ArrayList<>();
            if (updatedFieldDTO.getStaff() != null) {
                for (StaffDTO staffDto : updatedFieldDTO.getStaff()) {
                    StaffEntity staff = staffRepo.findById(staffDto.getId())
                            .orElseThrow(() -> new DataPersistException("Staff not found with ID: " + staffDto.getId()));
                    staffs.add(staff);
                }
            }
            field.setStaff(staffs);

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


}

