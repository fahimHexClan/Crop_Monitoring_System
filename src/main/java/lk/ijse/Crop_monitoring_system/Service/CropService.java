package lk.ijse.Crop_monitoring_system.Service;

import lk.ijse.Crop_monitoring_system.Dto.CropDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.CropStatus;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDto);

    void deleteCrop(Long cropCode);

    CropStatus getCrop(Long cropCode);

    void updateCrop(Long cropCode, CropDTO updateCropDTO);

    List<CropDTO> getAllCrop();

    List<Long> getAllCropIds();
}


