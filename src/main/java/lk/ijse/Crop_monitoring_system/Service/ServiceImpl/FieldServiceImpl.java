package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.CropDTO;
import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;
import lk.ijse.Crop_monitoring_system.Entity.CropEntity;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Exception.NoteNotFoundException;
import lk.ijse.Crop_monitoring_system.Repository.CropRepo;
import lk.ijse.Crop_monitoring_system.Repository.FieldRepo;
import lk.ijse.Crop_monitoring_system.Repository.StaffRepo;
import lk.ijse.Crop_monitoring_system.Service.FieldServise;
import lk.ijse.Crop_monitoring_system.util.Mapping;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldServise {
    @Autowired
    private FieldRepo fieldRepo;
    @Autowired
    private Mapping mapping;
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private CropRepo cropRepo;


    @Override
    public void saveField(FieldDTO fieldDTO) {
        /*List<StaffEntity> staffEntityList= new ArrayList<>();
        FieldEntity savedField = new FieldEntity();

        for(StaffDTO staffDTO: fieldDTO.getStaff()){
            staffEntityList.add(staffRepo.findById(staffDTO.getId()).get());
        }
        savedField.setStaff(staffEntityList);

        fieldRepo.save(savedField);
        List<StaffEntity> staff = savedField.getStaff();
        List<StaffDTO> staffDTOList = new ArrayList<>();
        for(StaffEntity staffEntity: staff){
            StaffDTO staffDTO = new StaffDTO();
            staffDTO.setId(staffEntity.getId());
            staffDTOList.add(staffDTO);
        }


//                this.fieldRepo.save(mapping.toFieldEntity(fieldDTO));
        if(savedField == null){
            throw new DataPersistException("Note not saved");
        }
    }*/
        // Convert DTO to FieldEntity
        FieldEntity fieldEntity = mapping.toFieldEntity(fieldDTO);
        fieldRepo.save(fieldEntity);

        // Handle crops and staff
        for (CropDTO cropDTO : fieldDTO.getCrops()) {
            CropEntity cropEntity = mapping.toCropEntity(cropDTO);
            // Check if crop already exists, if not, save it
            if (cropRepo.existsById(cropEntity.getId())) {
                cropRepo.save(cropEntity);
            }
        }

        for (StaffDTO staffDTO : fieldDTO.getStaff()) {
            StaffEntity staffEntity = mapping.toStaffEntity(staffDTO);
            // Check if staff already exists, if not, save it
            if (staffRepo.existsById(staffEntity.getId())) {
                staffRepo.save(staffEntity);
            }
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

            // Convert LocationDTO to Point using WKT string
            if (fieldDTO.getLocation() != null) {
                String locationWKT = "POINT (" + fieldDTO.getLocation().getLongitude() + " " + fieldDTO.getLocation().getLatitude() + ")";
                Point locationPoint = (Point) new WKTReader().read(locationWKT);
                existingField.setLocation(locationPoint);
            }

            existingField.setExtentSize(fieldDTO.getExtentSize());
            existingField.setFieldImage1(fieldDTO.getFieldImage1());
            existingField.setFieldImage2(fieldDTO.getFieldImage2());

            fieldRepo.save(existingField);
        }

    }