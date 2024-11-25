package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.EquipmentDTO;
import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.StaffStatus;
import lk.ijse.Crop_monitoring_system.Dto.VehicleDto;
import lk.ijse.Crop_monitoring_system.Entity.*;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Repository.*;
import lk.ijse.Crop_monitoring_system.Service.StaffService;
import lk.ijse.Crop_monitoring_system.util.Mapping;
import lk.ijse.Crop_monitoring_system.util.SelectedErrorStatus;
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
    private MonitorRepo monitoringLogRepo;

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
    }

    @Override
    public void deleteStaff(Long staffId) {
        Optional<StaffEntity> foundStaff = staffRepo.findById(staffId);
        if (!foundStaff.isPresent()) {
            throw new DataPersistException("Staff not found");
        } else {
            staffRepo.deleteById(staffId);
        }

    }

    @Override
    public StaffStatus getStaff(Long staffId) {
        if (staffRepo.existsById(staffId)) {
            StaffEntity selectedStaff = staffRepo.getReferenceById(staffId);
            return modelMapper1.toStaffDTO(selectedStaff);
        } else {
            return new SelectedErrorStatus(2, "Selected Staff not found");
        }

    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return modelMapper1.asStaffDTOList(staffRepo.findAll());

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


        }}}
