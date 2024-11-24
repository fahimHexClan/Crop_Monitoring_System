package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.CropDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.CropStatus;
import lk.ijse.Crop_monitoring_system.Entity.CropEntity;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Repository.CropRepo;
import lk.ijse.Crop_monitoring_system.Repository.FieldRepo;
import lk.ijse.Crop_monitoring_system.Service.CropService;
import lk.ijse.Crop_monitoring_system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
            if (cropDto.getFieldId() != null) {
                FieldEntity fieldEntity = fieldRepo.findById(cropDto.getFieldId()).orElseThrow(() -> new DataPersistException("Field not found with ID: " + cropDto.getFieldId()));
                cropEntity.setField(fieldEntity);
            }

            // Save the CropEntity
            cropRepo.save(cropEntity);
        } catch (Exception e) {
            throw new DataPersistException("Crop could not be saved: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteCrop(Long cropCode) {
        // Check if the Field exists before deletion
        Optional<CropEntity> foundCrop = cropRepo.findById(cropCode);
        if (foundCrop.isEmpty()) {
            throw new DataPersistException("Crop not found with ID: " + cropCode);
        }
        try {
            cropRepo.delete(foundCrop.get());
        } catch (Exception e) {
            throw new DataPersistException("Failed to delete crop with ID: " + cropCode, e);
        }
    }

    @Override
    public CropStatus getCrop(Long cropCode) {
        if (cropRepo.existsById(cropCode)) {
            CropEntity selectedCrop = cropRepo.getReferenceById(cropCode);
            return mapping.toCropDTO(selectedCrop);
        } else {
            throw new DataPersistException("Field not found");
        }

    }

    @Override
    public void updateCrop(Long cropCode, CropDTO updateCropDTO) {
        Optional<CropEntity> findCrop = cropRepo.findById(cropCode);
        if (!findCrop.isPresent()) {
            throw new DataPersistException("Field not found: " + cropCode);
        } else {
            CropEntity crop = findCrop.get();
            crop.setCommonName(updateCropDTO.getCommonName());
            crop.setScientificName(updateCropDTO.getScientificName());
            crop.setCropImage(updateCropDTO.getCropImage());
            crop.setCategory(updateCropDTO.getCategory());
            crop.setSeason(updateCropDTO.getSeason());

            if (updateCropDTO.getFieldId() != null) {
                FieldEntity fieldEntity = fieldRepo.findById(updateCropDTO.getFieldId())
                        .orElseThrow(() -> new DataPersistException("Field not found with ID: " + updateCropDTO.getFieldId()));
                crop.setField(fieldEntity);
            }


            // Save updated Field entity
            cropRepo.save(crop);
        }
    }
}
