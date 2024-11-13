package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.EquipmentDTO;
import lk.ijse.Crop_monitoring_system.Dto.VehicleDto;
import lk.ijse.Crop_monitoring_system.Entity.EquipmentEntity;
import lk.ijse.Crop_monitoring_system.Entity.VehicleEntity;
import lk.ijse.Crop_monitoring_system.Repository.EquipmentRepo;
import lk.ijse.Crop_monitoring_system.Service.EquipmentService;
import lk.ijse.Crop_monitoring_system.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EquipmentImpl implements EquipmentService {
    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addEquipment(EquipmentDTO equipmentDTO) {
        if (equipmentRepo.existsById(equipmentDTO.getId())) {
            return VarList.RSP_DUPLICATED;
        } else {
            equipmentRepo.save(modelMapper.map(equipmentDTO, EquipmentEntity.class));
            return VarList.RSP_SUCCESS;
        }
    }

    @Override
    public String updateEquipment(EquipmentDTO equipmentDTO) {
        if (equipmentRepo.existsById(equipmentDTO.getId())) {
            equipmentRepo.save(modelMapper.map(equipmentDTO, EquipmentEntity.class));
            return VarList.RSP_SUCCESS;
        } else {

            return VarList.RSP_DUPLICATED;
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        List<EquipmentEntity> equipmentEntityList = equipmentRepo.findAll();

        return modelMapper.map(equipmentEntityList, new TypeToken<ArrayList<EquipmentDTO>>() {

        }.getType());
    }

    @Override
    public EquipmentDTO getEquipmentById(Long equipmentId) {
        if (equipmentRepo.existsById(equipmentId)) {
            EquipmentEntity equipmentEntity = equipmentRepo.findById(equipmentId).orElse(null);
            return modelMapper.map(equipmentEntity, EquipmentDTO.class);
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
}
