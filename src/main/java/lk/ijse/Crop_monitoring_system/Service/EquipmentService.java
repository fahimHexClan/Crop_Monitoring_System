package lk.ijse.Crop_monitoring_system.Service;

import lk.ijse.Crop_monitoring_system.Dto.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    String addEquipment(EquipmentDTO equipmentDTO);

    String updateEquipment(EquipmentDTO equipmentDTO);

    List<EquipmentDTO> getAllEquipments();

    EquipmentDTO getEquipmentById(Long equipmentId);

    String deleteEquipment(Long equipmentID);

    List<Long> getAllEquipmentIds();
}
