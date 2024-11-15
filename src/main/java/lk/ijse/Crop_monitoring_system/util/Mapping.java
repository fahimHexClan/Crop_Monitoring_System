package lk.ijse.Crop_monitoring_system.util;

import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    //for user mapping
    private static final GeometryFactory geometryFactory = new GeometryFactory();

    //save
    public FieldEntity toFieldEntity(FieldDTO fieldDTO) {
        FieldEntity fieldEntity = modelMapper.map(fieldDTO, FieldEntity.class);

        // Convert location String to Point
        if (fieldDTO.getLocation() != null) {
            try {
                WKTReader reader = new WKTReader(geometryFactory);
                Point point = (Point) reader.read(fieldDTO.getLocation());
                fieldEntity.setLocation(point);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid location format: " + fieldDTO.getLocation());
            }
        }
        return fieldEntity;
    }

    public FieldDTO toFieldDTO(FieldEntity fieldEntity) {
        FieldDTO fieldDTO = modelMapper.map(fieldEntity, FieldDTO.class);

        // Convert Point to WKT String for location
        if (fieldEntity.getLocation() != null) {
            fieldDTO.setLocation(fieldEntity.getLocation().toText());  // Converts Point to WKT format
        }
        return fieldDTO;
    }

    //get all
    public List<FieldDTO> asFieldDTOList(List<FieldEntity> fieldEntities) {

        return modelMapper.map(fieldEntities, new TypeToken<List<FieldDTO>>() {
        }.getType());
    }

}