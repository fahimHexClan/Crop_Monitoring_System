package lk.ijse.Crop_monitoring_system.Service;

import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.FieldStatus;
import org.locationtech.jts.io.ParseException;

import java.util.List;

public interface FieldServise {

     void SaveField(FieldDTO fieldDto);

    void updateField(Long fieldCode, FieldDTO updatedFieldDTO);

    void deletefield(Long fieldCode);

    FieldStatus getField(Long fieldCode);

    List<FieldDTO> getAllField();
}
