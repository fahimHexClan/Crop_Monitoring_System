package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.CropDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.CropStatus;
import lk.ijse.Crop_monitoring_system.Entity.CropEntity;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.MonitoringLogEntity;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Repository.CropRepo;
import lk.ijse.Crop_monitoring_system.Repository.FieldRepo;
import lk.ijse.Crop_monitoring_system.Repository.MonitorRepo;
import lk.ijse.Crop_monitoring_system.Service.CropService;
import lk.ijse.Crop_monitoring_system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
    @Autowired
    private MonitorRepo monitorRepo;

    @Override
    public void saveCrop(CropDTO cropDto) {
        try {
            // Map CropDTO to CropEntity
            CropEntity cropEntity = mapping.toCropEntity(cropDto);

            // Ensure the ID is null for a new entity
            cropEntity.setId(null);

            // Validate Field ID and set the related field entity if present
            if (cropDto.getFieldId() != null) {
                FieldEntity fieldEntity = fieldRepo.findById(cropDto.getFieldId())
                        .orElseThrow(() -> new DataPersistException("Field not found with ID: " + cropDto.getFieldId()));
                cropEntity.setField(fieldEntity);
            } else {
                throw new DataPersistException("Field ID is required for saving a crop.");
            }

            // Validate Log ID and set the related MonitoringLogEntity if present
            if (cropDto.getLogId() != null) {
                MonitoringLogEntity logEntity = monitorRepo.findById(cropDto.getLogId())
                        .orElseThrow(() -> new DataPersistException("Log not found with ID: " + cropDto.getLogId()));
                cropEntity.setLog(logEntity);
            } else {
                throw new DataPersistException("Log ID is required for saving a crop.");
            }

            // Save the CropEntity
            cropRepo.save(cropEntity);
        } catch (NoSuchElementException e) {
            // Handle case when related entity is not found
            throw new DataPersistException("Referenced entity not found: " + e.getMessage(), e);
        } catch (DataAccessException e) {
            // Handle database access errors
            throw new DataPersistException("Database access error: " + e.getMessage(), e);
        } catch (Exception e) {
            // Handle any other unexpected errors
            throw new DataPersistException("Crop could not be saved: " + e.getMessage(), e);
        }}

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

    @Override
    public List<CropDTO> getAllCrop() {
        List<CropEntity> crops = cropRepo.findAll();
        if (crops.isEmpty()) {
            throw new DataPersistException("No fields found");
        }
        return mapping.asCropDTOList(crops);
    }
}
