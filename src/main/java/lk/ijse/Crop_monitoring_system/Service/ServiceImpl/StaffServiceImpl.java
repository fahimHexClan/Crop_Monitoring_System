package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.EquipmentDTO;
import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;
import lk.ijse.Crop_monitoring_system.Dto.VehicleDto;
import lk.ijse.Crop_monitoring_system.Entity.EquipmentEntity;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
import lk.ijse.Crop_monitoring_system.Entity.VehicleEntity;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Repository.EquipmentRepo;
import lk.ijse.Crop_monitoring_system.Repository.FieldRepo;
import lk.ijse.Crop_monitoring_system.Repository.StaffRepo;
import lk.ijse.Crop_monitoring_system.Repository.VehicleRepo;
import lk.ijse.Crop_monitoring_system.Service.StaffService;
import lk.ijse.Crop_monitoring_system.util.Mapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private FieldRepo fieldRepo;
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Mapping modelMapper1;



    public void saveStaff(StaffDTO staffDTO) {
        StaffEntity staff = modelMapper1.toStaffEntity(staffDTO);

        // Fetch and set associated fields
        List<FieldEntity> fields = new ArrayList<>();
        if (staffDTO.getFields() != null) {
            for (FieldDTO fieldDto : staffDTO.getFields()) {
                FieldEntity field = fieldRepo.findById(fieldDto.getFieldId())
                        .orElseThrow(() -> new DataPersistException("Field not found with ID: " + fieldDto.getFieldId()));
                fields.add(field);
            }
        }
        staff.setFields(fields);

        // Save staff entity
        StaffEntity savedStaff = staffRepo.save(staff);
        if (savedStaff == null) {
            throw new DataPersistException("Staff not saved");
        }





        /*try {
            // Step 1: Check if a staff member with the same email already exists
            if (staffRepo.findByEmail(staffDTO.getEmail()).isPresent()) {
                throw new DataPersistException("A staff member with the provided email already exists: " + staffDTO.getEmail());
            }

            // Step 2: Create a new StaffEntity and set basic information
            StaffEntity staffEntity = modelMapper.map(staffDTO, StaffEntity.class);

            // Step 3: Handle Many-to-Many relationship with Fields using IDs
            if (staffDTO.getFields() != null && !staffDTO.getFields().isEmpty()) {
                for (FieldDTO fieldDto : staffDTO.getFields()) {
                    FieldEntity fieldEntity = fieldRepo.findById(fieldDto.getFieldId())
                            .orElseThrow(() -> new DataPersistException("Field not found with ID: " + fieldDto.getFieldId()));
                    staffEntity.addField(fieldEntity); // Synchronize both ways
                }
            }

            // Step 4: Handle One-to-Many relationship with Vehicles using IDs (if provided)
            if (staffDTO.getVehicles() != null && !staffDTO.getVehicles().isEmpty()) {
                for (VehicleDto vehicleDto : staffDTO.getVehicles()) {
                    VehicleEntity vehicleEntity = vehicleRepo.findById(vehicleDto.getId())
                            .orElseThrow(() -> new DataPersistException("Vehicle not found with ID: " + vehicleDto.getId()));
                    staffEntity.addVehicle(vehicleEntity); // Synchronize both ways
                }
            }

            // Step 5: Handle One-to-Many relationship with Equipments using IDs (if provided)
            if (staffDTO.getEquipmentDTOS() != null && !staffDTO.getEquipmentDTOS().isEmpty()) {
                for (EquipmentDTO equipmentDto : staffDTO.getEquipmentDTOS()) {
                    EquipmentEntity equipmentEntity = equipmentRepo.findById(equipmentDto.getId())
                            .orElseThrow(() -> new DataPersistException("Equipment not found with ID: " + equipmentDto.getId()));
                    staffEntity.addEquipment(equipmentEntity); // Synchronize both ways
                }
            }

            // Step 6: Save Staff entity
            staffRepo.save(staffEntity);

        } catch (DataIntegrityViolationException e) {
            throw new DataPersistException("A staff member with the provided email already exists: " + staffDTO.getEmail(), e);
        } catch (Exception e) {
            throw new DataPersistException("Error occurred while saving staff: " + e.getMessage(), e);
        }*/
    }


    @Override
    public void updateStaff(Long staffCode, StaffDTO updatedStaffDTO) {
        try {
            // Find the existing staff member
            StaffEntity staffEntity = staffRepo.findById(staffCode)
                    .orElseThrow(() -> new DataPersistException("Staff member not found with ID: " + staffCode));

            // Update fields from updatedStaffDTO
            staffEntity.setFirstName(updatedStaffDTO.getFirstName());
            staffEntity.setLastName(updatedStaffDTO.getLastName());
            staffEntity.setDesignation(updatedStaffDTO.getDesignation());
            staffEntity.setGender(updatedStaffDTO.getGender());
            staffEntity.setJoinedDate(updatedStaffDTO.getJoinedDate());
            staffEntity.setDob(updatedStaffDTO.getDob());
            staffEntity.setAddressLine1(updatedStaffDTO.getAddressLine1());
            staffEntity.setAddressLine2(updatedStaffDTO.getAddressLine2());
            staffEntity.setAddressLine3(updatedStaffDTO.getAddressLine3());
            staffEntity.setAddressLine4(updatedStaffDTO.getAddressLine4());
            staffEntity.setAddressLine5(updatedStaffDTO.getAddressLine5());
            staffEntity.setContactNo(updatedStaffDTO.getContactNo());
            staffEntity.setEmail(updatedStaffDTO.getEmail());
            staffEntity.setRole(updatedStaffDTO.getRole());

            // Update Many-to-Many relationship with Fields using IDs
            staffEntity.getFields().clear();
            if (updatedStaffDTO.getFields() != null && !updatedStaffDTO.getFields().isEmpty()) {
                for (FieldDTO fieldDto : updatedStaffDTO.getFields()) {
                    FieldEntity fieldEntity = fieldRepo.findById(fieldDto.getFieldId())
                            .orElseThrow(() -> new DataPersistException("Field not found with ID: " + fieldDto.getFieldId()));
                    staffEntity.addField(fieldEntity); // Synchronize both ways
                }
            }

            // Update One-to-Many relationship with Vehicles using IDs
            staffEntity.getVehicles().clear();
            if (updatedStaffDTO.getVehicles() != null && !updatedStaffDTO.getVehicles().isEmpty()) {
                for (VehicleDto vehicleDto : updatedStaffDTO.getVehicles()) {
                    VehicleEntity vehicleEntity = vehicleRepo.findById(vehicleDto.getId())
                            .orElseThrow(() -> new DataPersistException("Vehicle not found with ID: " + vehicleDto.getId()));
                    staffEntity.addVehicle(vehicleEntity); // Synchronize both ways
                }
            }

            // Update One-to-Many relationship with Equipments using IDs
            staffEntity.getEquipments().clear();
            if (updatedStaffDTO.getEquipmentDTOS() != null && !updatedStaffDTO.getEquipmentDTOS().isEmpty()) {
                for (EquipmentDTO equipmentDto : updatedStaffDTO.getEquipmentDTOS()) {
                    EquipmentEntity equipmentEntity = equipmentRepo.findById(equipmentDto.getId())
                            .orElseThrow(() -> new DataPersistException("Equipment not found with ID: " + equipmentDto.getId()));
                    staffEntity.addEquipment(equipmentEntity); // Synchronize both ways
                }
            }

            // Save updated Staff entity
            staffRepo.save(staffEntity);

        } catch (Exception e) {
            throw new DataPersistException("Error occurred while updating staff: " + e.getMessage(), e);
        }
    }

}