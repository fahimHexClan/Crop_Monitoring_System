package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Exception.NoteNotFoundException;
import lk.ijse.Crop_monitoring_system.Repository.FieldRepo;
import lk.ijse.Crop_monitoring_system.Service.FieldServise;
import lk.ijse.Crop_monitoring_system.util.Mapping;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldServise {
    @Autowired
    private FieldRepo fieldRepo;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldDTO fieldDTO) {

        FieldEntity savedField =
                this.fieldRepo.save(mapping.toFieldEntity(fieldDTO));
        if(savedField == null){
            throw new DataPersistException("Note not saved");
        }
    }

    @Override
    public List<FieldDTO> getAllField() {
        List<FieldEntity> allUsers = fieldRepo.findAll();
        return mapping.asFieldDTOList(allUsers);
    }

    @Override
    public void deletefield(Long fieldId) {
        Optional<FieldEntity> foundNote = fieldRepo.findById(fieldId);
        if (!foundNote.isPresent()) {
            throw new NoteNotFoundException("Note not found");
        }else {
            fieldRepo.deleteById(fieldId);
        }
    }

    @Override
    public void updateField(Long fieldId, FieldDTO fieldDTO) throws ParseException {
        FieldEntity existingField = fieldRepo.findById(fieldId)
                .orElseThrow(() -> new NoteNotFoundException("Field not found"));

        existingField.setFieldName(fieldDTO.getFieldName());
        if (fieldDTO.getLocation() != null) {
            Point locationPoint = (Point) new WKTReader().read(fieldDTO.getLocation());
            existingField.setLocation(locationPoint);
        }
        existingField.setExtentSize(fieldDTO.getExtentSize());
        existingField.setFieldImage1(fieldDTO.getFieldImage1());
        existingField.setFieldImage2(fieldDTO.getFieldImage2());

        // Add logging to verify the updates
        System.out.println("Updating Field: " + existingField);

        fieldRepo.save(existingField);
    }

}