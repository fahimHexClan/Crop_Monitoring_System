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
import lk.ijse.Crop_monitoring_system.util.SelectedErrorStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class CropServiceImpl implements CropService {

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
        CropEntity cropEntity = mapping.toCropEntity(cropDto);  // Mapping DTO to Entity
        CropEntity savedCrop = cropRepo.save(cropEntity);
        if (savedCrop == null) {
            throw new DataPersistException("CROP not saved");
        }
    }

    @Override
    public CropStatus getCrop(Long cropId) {
        if (cropRepo.existsById(cropId)) {
            var selectedCrop = cropRepo.getReferenceById(cropId);
            return mapping.toCropDTO(selectedCrop);
        } else {
            return new SelectedErrorStatus(2, "Selected note not found");
        }
    }

    @Override
    public List<CropDTO> getAllCrop() {
        return mapping.asNoteDTOList(cropRepo.findAll());
    }

    @Override
    public List<Long> getAllCropIds() {
        try {
            List<CropEntity> crops = cropRepo.findAll();
            return crops.stream().map(CropEntity::getId).collect(Collectors.toList());
        } catch (Exception e) {
            throw new DataPersistException("Failed to retrieve crop IDs", e);
        }
    }

    @Override
    public void deleteCrop(Long cropId) {
        Optional<CropEntity> foundCrop = cropRepo.findById(cropId);
        if (!foundCrop.isPresent()) {
            throw new DataPersistException("Note not found");
        } else {
            cropRepo.deleteById(cropId);
        }
    }

    @Override
    public void updateCrop(Long CropId, CropDTO cropDto) {
        Optional<CropEntity> findNote = cropRepo.findById(CropId);
        if (!findNote.isPresent()) {
            throw new DataPersistException("Crop not found");
        } else {
            CropEntity crop = findNote.get();
            crop.setCategory(cropDto.getCategory());
            crop.setCropImage(cropDto.getCropImage());
            crop.setSeason(cropDto.getSeason());
            crop.setCommonName(cropDto.getCommonName());
            crop.setScientificName(cropDto.getScientificName());

            FieldEntity fieldEntity = fieldRepo.findById(cropDto.getFieldId())
                    .orElseThrow(() -> new DataPersistException("Field not found"));
            MonitoringLogEntity monitoringLogEntity = monitorRepo.findById(cropDto.getLogId())
                    .orElseThrow(() -> new DataPersistException("Log not found"));

            crop.setField(fieldEntity);
            crop.setLog(monitoringLogEntity);

            cropRepo.save(crop);

        }
    }

}
