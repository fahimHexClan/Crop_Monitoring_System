package lk.ijse.Crop_monitoring_system.Service;

import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import org.locationtech.jts.io.ParseException;

import java.util.List;

public interface FieldServise {
    void saveField(FieldDTO fieldDTO);

    List<FieldDTO> getAllField();

    void deletefield(Long fieldId);

    void updateField(Long fieldId, FieldDTO fieldDTO) throws ParseException;
}
