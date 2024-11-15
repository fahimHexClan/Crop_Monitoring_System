package lk.ijse.Crop_monitoring_system.util;

import lk.ijse.Crop_monitoring_system.Dto.CropDTO;
import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Dto.LocationDTO;
import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;
import lk.ijse.Crop_monitoring_system.Entity.CropEntity;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    private static final GeometryFactory geometryFactory = new GeometryFactory();

    // Convert FieldDTO to FieldEntity
    public FieldEntity toFieldEntity(FieldDTO fieldDTO) {
        FieldEntity fieldEntity = modelMapper.map(fieldDTO, FieldEntity.class);

        // Convert location from DTO (latitude, longitude) to Point
        if (fieldDTO.getLocation() != null) {
            String locationWKT = "POINT (" + fieldDTO.getLocation().getLongitude() + " " + fieldDTO.getLocation().getLatitude() + ")";
            WKTReader reader = new WKTReader();
            try {
                Point locationPoint = (Point) reader.read(locationWKT);
                fieldEntity.setLocation(locationPoint);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid location format");
            }
        }

        return fieldEntity;
    }

    // Convert FieldEntity to FieldDTO
    public FieldDTO toFieldDTO(FieldEntity fieldEntity) {
        FieldDTO fieldDTO = modelMapper.map(fieldEntity, FieldDTO.class);

        // Convert Point to LocationDTO
        if (fieldEntity.getLocation() != null) {
            Coordinate coordinate = fieldEntity.getLocation().getCoordinate();
            LocationDTO locationDTO = new LocationDTO(coordinate.y, coordinate.x);  // Create LocationDTO from latitude and longitude
            fieldDTO.setLocation(locationDTO);
        }

        return fieldDTO;
    }

    // Convert list of FieldEntity to list of FieldDTO
    public List<FieldDTO> asFieldDTOList(List<FieldEntity> fieldEntities) {
        return modelMapper.map(fieldEntities, new TypeToken<List<FieldDTO>>(){}.getType());
    }

    // Convert CropDTO to CropEntity
    public CropEntity toCropEntity(CropDTO cropDTO) {
        // Use modelMapper to map basic fields
        CropEntity cropEntity = modelMapper.map(cropDTO, CropEntity.class);

        // Handle field reference (assuming field is an ID in DTO)
        if (cropDTO.getField() != null) {
            FieldEntity fieldEntity = new FieldEntity();
            fieldEntity.setFieldId(Long.valueOf(cropDTO.getField()));  // Get fieldId from FieldDTO and convert it to Long
            cropEntity.setField(fieldEntity);
        }

        return cropEntity;
    }

    // Convert StaffDTO to StaffEntity
    public StaffEntity toStaffEntity(StaffDTO staffDTO) {
        // Use modelMapper to map basic fields
        StaffEntity staffEntity = modelMapper.map(staffDTO, StaffEntity.class);

        // Handle fields mapping (assuming fields are IDs in the DTO)
        if (staffDTO.getFields() != null && !staffDTO.getFields().isEmpty()) {
            List<FieldEntity> fieldEntities = staffDTO.getFields().stream()
                    .map(fieldDTO -> {
                        FieldEntity fieldEntity = new FieldEntity();
                        fieldEntity.setFieldId(Long.valueOf(fieldDTO.getFieldId()));  // Get fieldId from FieldDTO and convert it to Long
                        return fieldEntity;
                    })
                    .collect(Collectors.toList());
            staffEntity.setFields(fieldEntities);
        }

        return staffEntity;
    }
}
