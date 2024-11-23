package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.CropDTO;
import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;
import lk.ijse.Crop_monitoring_system.Entity.CropEntity;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Repository.CropRepo;
import lk.ijse.Crop_monitoring_system.Repository.FieldRepo;
import lk.ijse.Crop_monitoring_system.Repository.StaffRepo;
import lk.ijse.Crop_monitoring_system.Service.CropService;
import lk.ijse.Crop_monitoring_system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CropSerrviceImpl implements CropService {
    @Autowired
    private CropRepo cropRepo;
    @Autowired
    private Mapping mapping;
    @Autowired
    private FieldRepo fieldRepo;

    @Override
    public void saveCrop(CropDTO cropDto) {
        try {
            // Map CropDTO to CropEntity
            CropEntity cropEntity = mapping.toCropEntity(cropDto);

            // If there's a field associated, set it
            if (cropDto.getField() != null) {
                FieldEntity fieldEntity = fieldRepo.findById(cropDto.getField())
                        .orElseThrow(() -> new DataPersistException("Field not found with ID: " + cropDto.getField()));
                cropEntity.setField(fieldEntity);
            }

            // Save the CropEntity
            cropRepo.save(cropEntity);
        } catch (Exception e) {
            throw new DataPersistException("Crop could not be saved: " + e.getMessage(), e);
        }}
}
