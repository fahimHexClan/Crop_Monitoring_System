package lk.ijse.Crop_monitoring_system.Service;

import lk.ijse.Crop_monitoring_system.Dto.CropDTO;

public interface CropService {
    void saveCrop(CropDTO cropDto);

    void deleteCrop(Long cropCode);
}
