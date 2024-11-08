package lk.ijse.Crop_monitoring_system.Service;

import lk.ijse.Crop_monitoring_system.Dto.FieldDto;
import lk.ijse.Crop_monitoring_system.Dto.Request.SaveFieldRequestDto;

public interface FieldService {


    String Save_Fields(FieldDto fieldDto);
}
