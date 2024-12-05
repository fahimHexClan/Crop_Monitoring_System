package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.EquipmentDTO;
import lk.ijse.Crop_monitoring_system.Entity.EquipmentEntity;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Repository.EquipmentRepo;
import lk.ijse.Crop_monitoring_system.Repository.FieldRepo;
import lk.ijse.Crop_monitoring_system.Repository.StaffRepo;
import lk.ijse.Crop_monitoring_system.Service.EquipmentService;
import lk.ijse.Crop_monitoring_system.util.VarList;
import lk.ijse.Crop_monitoring_system.util.mappers.EquipmentMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentImpl implements EquipmentService {
    private final EquipmentMapper equipmentMapper;
    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private StaffRepo staffRepository;
    @Autowired
    private FieldRepo fieldRepository;

    @Override
    public String addEquipment(EquipmentDTO equipmentDTO) {


        if (equipmentRepo.existsById(equipmentDTO.getId())) {
            return VarList.RSP_DUPLICATED;  // Return duplicate response if exists
        }


        StaffEntity assignedStaff = staffRepository.findById(equipmentDTO.getAssignedStaff()).orElseThrow(() -> new RuntimeException("Staff not found with ID: " + equipmentDTO.getAssignedStaff()));


        FieldEntity assignedField = fieldRepository.findById(equipmentDTO.getAssignedField()).orElseThrow(() -> new RuntimeException("Field not found with ID: " + equipmentDTO.getAssignedField()));


        EquipmentEntity equipmentEntity = modelMapper.map(equipmentDTO, EquipmentEntity.class);


        equipmentEntity.setAssignedStaff(assignedStaff);
        equipmentEntity.setAssignedField(assignedField);


        equipmentRepo.save(equipmentEntity);

        return VarList.RSP_SUCCESS;
    }

    @Override
    public String updateEquipment(EquipmentDTO equipmentDTO) {
        if (!equipmentRepo.existsById(equipmentDTO.getId())) {
            return VarList.RSP_DUPLICATED;
        }
        StaffEntity assignedStaff = staffRepository.findById(equipmentDTO.getAssignedStaff()).orElseThrow(() -> new RuntimeException("Staff not found with ID: " + equipmentDTO.getAssignedStaff()));
        FieldEntity assignedField = fieldRepository.findById(equipmentDTO.getAssignedField()).orElseThrow(() -> new RuntimeException("Field not found with ID: " + equipmentDTO.getAssignedField()));
        EquipmentEntity existingEquipment = equipmentRepo.findById(equipmentDTO.getId()).orElse(null);

        if (existingEquipment != null) {
            existingEquipment.setName(equipmentDTO.getName());
            existingEquipment.setType(equipmentDTO.getType());
            existingEquipment.setStatus(equipmentDTO.getStatus());
            existingEquipment.setAssignedStaff(assignedStaff);
            existingEquipment.setAssignedField(assignedField);

            equipmentRepo.save(existingEquipment);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_DUPLICATED;
        }
    }
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
    @Override
    public String deleteEquipment(Long equipmentID) {
        if (equipmentRepo.existsById(equipmentID)) {
            equipmentRepo.deleteById(equipmentID);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_DUPLICATED;
        }
    }
    @Override
    public List<Long> getAllEquipmentIds() {
        try {
            List<EquipmentEntity> equipments = equipmentRepo.findAll();
            return equipments.stream().map(EquipmentEntity::getId).collect(Collectors.toList());
        } catch (Exception e) {
            throw new DataPersistException("Failed to retrieve equipment IDs", e);
        }
    }
}
