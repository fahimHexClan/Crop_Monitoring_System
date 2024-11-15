package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.EquipmentDTO;
import lk.ijse.Crop_monitoring_system.Dto.VehicleDto;
import lk.ijse.Crop_monitoring_system.Entity.EquipmentEntity;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
import lk.ijse.Crop_monitoring_system.Entity.VehicleEntity;
import lk.ijse.Crop_monitoring_system.Repository.EquipmentRepo;
import lk.ijse.Crop_monitoring_system.Repository.FieldRepo;
import lk.ijse.Crop_monitoring_system.Repository.StaffRepo;
import lk.ijse.Crop_monitoring_system.Service.EquipmentService;
import lk.ijse.Crop_monitoring_system.util.VarList;
import lk.ijse.Crop_monitoring_system.util.mappers.EquipmentMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentImpl implements EquipmentService {
    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private StaffRepo staffRepository;
    @Autowired
    private FieldRepo fieldRepository;

    private final EquipmentMapper equipmentMapper;
    @Override
    public String addEquipment(EquipmentDTO equipmentDTO) {



            // Check if the equipment already exists
            if (equipmentRepo.existsById(equipmentDTO.getId())) {
                return VarList.RSP_DUPLICATED;  // Return duplicate response if exists
            }

            // Fetch the StaffEntity by ID
            StaffEntity assignedStaff = staffRepository.findById(equipmentDTO.getAssignedStaff())
                    .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + equipmentDTO.getAssignedStaff()));

            // Fetch the FieldEntity by ID
            FieldEntity assignedField = fieldRepository.findById(equipmentDTO.getAssignedField())
                    .orElseThrow(() -> new RuntimeException("Field not found with ID: " + equipmentDTO.getAssignedField()));

            // Map the DTO to the entity
            EquipmentEntity equipmentEntity = modelMapper.map(equipmentDTO, EquipmentEntity.class);

            // Set the staff and field entities in the equipment entity
            equipmentEntity.setAssignedStaff(assignedStaff);
            equipmentEntity.setAssignedField(assignedField);

            // Save the EquipmentEntity
            equipmentRepo.save(equipmentEntity);

            return VarList.RSP_SUCCESS;  // Return success response
        }

    @Override
    public String updateEquipment(EquipmentDTO equipmentDTO) {
        if (!equipmentRepo.existsById(equipmentDTO.getId())) {
            return VarList.RSP_DUPLICATED;  // If the equipment doesn't exist, return duplicate response
        }

        // Fetch the StaffEntity by ID
        StaffEntity assignedStaff = staffRepository.findById(equipmentDTO.getAssignedStaff())
                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + equipmentDTO.getAssignedStaff()));

        // Fetch the FieldEntity by ID
        FieldEntity assignedField = fieldRepository.findById(equipmentDTO.getAssignedField())
                .orElseThrow(() -> new RuntimeException("Field not found with ID: " + equipmentDTO.getAssignedField()));

        // Retrieve the existing EquipmentEntity from the database
        EquipmentEntity existingEquipment = equipmentRepo.findById(equipmentDTO.getId()).orElse(null);

        if (existingEquipment != null) {
            // Map the DTO to the existing entity
            existingEquipment.setName(equipmentDTO.getName());
            existingEquipment.setType(equipmentDTO.getType());
            existingEquipment.setStatus(equipmentDTO.getStatus());
            existingEquipment.setAssignedStaff(assignedStaff);  // Set the new staff entity
            existingEquipment.setAssignedField(assignedField);  // Set the new field entity

            // Save the updated EquipmentEntity
            equipmentRepo.save(existingEquipment);

            return VarList.RSP_SUCCESS;  // Return success response
        } else {
            return VarList.RSP_DUPLICATED;  // If no equipment is found, return duplicate response
        }
    }

//    @Override
//    public List<EquipmentDTO> getAllEquipments() {
//        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
//        List<EquipmentEntity> equipmentEntityList = equipmentRepo.findAll();
//        for(EquipmentEntity equipmentEntity : equipmentEntityList) {
//            EquipmentDTO equipmentDTO =new EquipmentDTO(
//                    equipmentEntity.getId(),
//                    equipmentEntity.getName(),
//                    equipmentEntity.getType(),
//                    equipmentEntity.getStatus(),
//                    equipmentEntity.getAssignedStaff().getId(),
//                    equipmentEntity.getAssignedField().getFieldId()
//            );
//            equipmentDTOList.add(equipmentDTO);
//        }
//       return equipmentDTOList;
//    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        List<EquipmentEntity> equipmentEntityList = equipmentRepo.findAll();
        List<EquipmentDTO> equipmentDTOList = equipmentMapper.entityListToDTOList(equipmentEntityList);
        return equipmentDTOList;
    }

    @Override
    public EquipmentDTO getEquipmentById(Long equipmentId) {
        if (equipmentRepo.existsById(equipmentId)) {
            Optional<EquipmentEntity> byId = equipmentRepo.findById(equipmentId);
            return equipmentMapper.entityToDTO(byId.get());
        } else {
            return null;
        }
    }

//    @Override
//    public EquipmentDTO getEquipmentById(Long equipmentId) {
//        if (equipmentRepo.existsById(equipmentId)) {
//            EquipmentEntity equipmentEntity = equipmentRepo.findById(equipmentId).orElse(null);
//            return new EquipmentDTO(
//                    equipmentEntity.getId(),
//                    equipmentEntity.getName(),
//                    equipmentEntity.getType(),
//                    equipmentEntity.getStatus(),
//                    equipmentEntity.getAssignedStaff().getId(),
//                    equipmentEntity.getAssignedField().getFieldId()
//            );
//        } else {
//            return null;
//        }
//    }

    @Override
    public String deleteEquipment(Long equipmentID) {
        if (equipmentRepo.existsById(equipmentID)) {
            equipmentRepo.deleteById(equipmentID);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_DUPLICATED;
        }
    }
}
