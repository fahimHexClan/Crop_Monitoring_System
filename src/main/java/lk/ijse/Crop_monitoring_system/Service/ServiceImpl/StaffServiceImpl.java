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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

            // Fetch and set associated vehicles
            List<VehicleEntity> vehicles = new ArrayList<>();
            if (staffDTO.getVehicles() != null) {
                for (VehicleDto vehicleDto : staffDTO.getVehicles()) {
                    VehicleEntity vehicle = vehicleRepo.findById(vehicleDto.getId())
                            .orElseThrow(() -> new DataPersistException("Vehicle not found with ID: " + vehicleDto.getId()));
                    vehicles.add(vehicle);
                }
            }
            staff.setVehicles(vehicles);

            // Fetch and set associated equipment
            List<EquipmentEntity> equipmentEntities = new ArrayList<>();
            if (staffDTO.getEquipmentDTOS() != null) {
                for (EquipmentDTO equipmentDto : staffDTO.getEquipmentDTOS()) {
                    EquipmentEntity equipment = equipmentRepo.findById(equipmentDto.getId())
                            .orElseThrow(() -> new DataPersistException("Equipment not found with ID: " + equipmentDto.getId()));
                    equipmentEntities.add(equipment);
                }
            }
            staff.setEquipments(equipmentEntities);

            // Save staff entity
            staffRepo.save(staff);
        }

        @Override
    public void updateStaff(Long staffCode, StaffDTO updatedStaffDTO) {
                Optional<StaffEntity> findStaff = staffRepo.findById(staffCode);
                if (!findStaff.isPresent()) {
                    throw new DataPersistException("Staff not found with ID: " + staffCode);
                } else {
                    StaffEntity staff = findStaff.get();
                    // Update basic fields
                    staff.setFirstName(updatedStaffDTO.getFirstName());
                    staff.setLastName(updatedStaffDTO.getLastName());
                    staff.setDesignation(updatedStaffDTO.getDesignation());
                    staff.setGender(updatedStaffDTO.getGender());
                    staff.setJoinedDate(updatedStaffDTO.getJoinedDate());
                    staff.setDob(updatedStaffDTO.getDob());
                    staff.setAddressLine1(updatedStaffDTO.getAddressLine1());
                    staff.setAddressLine2(updatedStaffDTO.getAddressLine2());
                    staff.setAddressLine3(updatedStaffDTO.getAddressLine3());
                    staff.setAddressLine4(updatedStaffDTO.getAddressLine4());
                    staff.setAddressLine5(updatedStaffDTO.getAddressLine5());
                    staff.setContactNo(updatedStaffDTO.getContactNo());
                    staff.setEmail(updatedStaffDTO.getEmail());
                    staff.setRole(updatedStaffDTO.getRole());

                    // Update Many-to-Many relationship with Fields
                    List<FieldEntity> fields = new ArrayList<>();
                    if (updatedStaffDTO.getFields() != null) {
                        for (FieldDTO fieldDto : updatedStaffDTO.getFields()) {
                            FieldEntity field = fieldRepo.findById(fieldDto.getFieldId())
                                    .orElseThrow(() -> new DataPersistException("Field not found with ID: " + fieldDto.getFieldId()));
                            fields.add(field);
                        }
                    }
                    staff.setFields(fields);

                    // Update One-to-Many relationship with Vehicles
                    staff.getVehicles().clear();
                    if (updatedStaffDTO.getVehicles() != null) {
                        for (VehicleDto vehicleDto : updatedStaffDTO.getVehicles()) {
                            VehicleEntity vehicle = vehicleRepo.findById(vehicleDto.getId())
                                    .orElseThrow(() -> new DataPersistException("Vehicle not found with ID: " + vehicleDto.getId()));
                            vehicle.setStaff(staff); // Synchronize both ways
                            staff.getVehicles().add(vehicle);
                        }
                    }

                    // Update One-to-Many relationship with Equipments
                    staff.getEquipments().clear();
                    if (updatedStaffDTO.getEquipmentDTOS() != null) {
                        for (EquipmentDTO equipmentDto : updatedStaffDTO.getEquipmentDTOS()) {
                            EquipmentEntity equipment = equipmentRepo.findById(equipmentDto.getId())
                                    .orElseThrow(() -> new DataPersistException("Equipment not found with ID: " + equipmentDto.getId()));
                            equipment.setAssignedStaff(staff); // Synchronize both ways
                            staff.getEquipments().add(equipment);
                        }
                    }

                    // Save updated Staff entity
                    staffRepo.save(staff);
                }
            }
        }
