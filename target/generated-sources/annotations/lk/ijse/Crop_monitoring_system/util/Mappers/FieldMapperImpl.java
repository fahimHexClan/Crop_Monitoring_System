package lk.ijse.Crop_monitoring_system.util.Mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import lk.ijse.Crop_monitoring_system.Dto.CropDTO;
import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;
import lk.ijse.Crop_monitoring_system.Dto.VehicleDto;
import lk.ijse.Crop_monitoring_system.Entity.CropEntity;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
import lk.ijse.Crop_monitoring_system.Entity.VehicleEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-10T21:59:06+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class FieldMapperImpl implements FieldMapper {

    @Override
    public FieldEntity RequesDtoToEntity(FieldDTO fieldDto) {
        if ( fieldDto == null ) {
            return null;
        }

        FieldEntity fieldEntity = new FieldEntity();

        fieldEntity.setFieldCode( fieldDto.getFieldCode() );
        fieldEntity.setFieldName( fieldDto.getFieldName() );
        fieldEntity.setLocation( fieldDto.getLocation() );
        fieldEntity.setExtentSize( fieldDto.getExtentSize() );
        fieldEntity.setFieldImage1( fieldDto.getFieldImage1() );
        fieldEntity.setFieldImage2( fieldDto.getFieldImage2() );
        fieldEntity.setCrops( cropDTOListToCropEntityList( fieldDto.getCrops() ) );
        fieldEntity.setStaff( staffDTOListToStaffEntityList( fieldDto.getStaff() ) );

        return fieldEntity;
    }

    protected CropEntity cropDTOToCropEntity(CropDTO cropDTO) {
        if ( cropDTO == null ) {
            return null;
        }

        CropEntity cropEntity = new CropEntity();

        cropEntity.setId( cropDTO.getId() );
        cropEntity.setCropCode( cropDTO.getCropCode() );
        cropEntity.setCommonName( cropDTO.getCommonName() );
        cropEntity.setScientificName( cropDTO.getScientificName() );
        cropEntity.setCropImage( cropDTO.getCropImage() );
        cropEntity.setCategory( cropDTO.getCategory() );
        cropEntity.setSeason( cropDTO.getSeason() );
        cropEntity.setField( RequesDtoToEntity( cropDTO.getField() ) );

        return cropEntity;
    }

    protected List<CropEntity> cropDTOListToCropEntityList(List<CropDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<CropEntity> list1 = new ArrayList<CropEntity>( list.size() );
        for ( CropDTO cropDTO : list ) {
            list1.add( cropDTOToCropEntity( cropDTO ) );
        }

        return list1;
    }

    protected List<FieldEntity> fieldDTOListToFieldEntityList(List<FieldDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<FieldEntity> list1 = new ArrayList<FieldEntity>( list.size() );
        for ( FieldDTO fieldDTO : list ) {
            list1.add( RequesDtoToEntity( fieldDTO ) );
        }

        return list1;
    }

    protected List<StaffEntity> staffDTOListToStaffEntityList(List<StaffDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<StaffEntity> list1 = new ArrayList<StaffEntity>( list.size() );
        for ( StaffDTO staffDTO : list ) {
            list1.add( staffDTOToStaffEntity( staffDTO ) );
        }

        return list1;
    }

    protected VehicleEntity vehicleDtoToVehicleEntity(VehicleDto vehicleDto) {
        if ( vehicleDto == null ) {
            return null;
        }

        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.setId( vehicleDto.getId() );
        vehicleEntity.setVehicleCode( vehicleDto.getVehicleCode() );
        vehicleEntity.setLicensePlateNumber( vehicleDto.getLicensePlateNumber() );
        vehicleEntity.setCategory( vehicleDto.getCategory() );
        vehicleEntity.setFuelType( vehicleDto.getFuelType() );
        vehicleEntity.setStatus( vehicleDto.getStatus() );
        vehicleEntity.setStaff( staffDTOListToStaffEntityList( vehicleDto.getStaff() ) );
        vehicleEntity.setRemarks( vehicleDto.getRemarks() );

        return vehicleEntity;
    }

    protected List<VehicleEntity> vehicleDtoListToVehicleEntityList(List<VehicleDto> list) {
        if ( list == null ) {
            return null;
        }

        List<VehicleEntity> list1 = new ArrayList<VehicleEntity>( list.size() );
        for ( VehicleDto vehicleDto : list ) {
            list1.add( vehicleDtoToVehicleEntity( vehicleDto ) );
        }

        return list1;
    }

    protected StaffEntity staffDTOToStaffEntity(StaffDTO staffDTO) {
        if ( staffDTO == null ) {
            return null;
        }

        StaffEntity staffEntity = new StaffEntity();

        staffEntity.setId( staffDTO.getId() );
        staffEntity.setStaffCode( staffDTO.getStaffCode() );
        staffEntity.setFirstName( staffDTO.getFirstName() );
        staffEntity.setLastName( staffDTO.getLastName() );
        staffEntity.setDesignation( staffDTO.getDesignation() );
        staffEntity.setGender( staffDTO.getGender() );
        staffEntity.setJoinedDate( staffDTO.getJoinedDate() );
        staffEntity.setDob( staffDTO.getDob() );
        staffEntity.setAddressLine1( staffDTO.getAddressLine1() );
        staffEntity.setAddressLine2( staffDTO.getAddressLine2() );
        staffEntity.setAddressLine3( staffDTO.getAddressLine3() );
        staffEntity.setAddressLine4( staffDTO.getAddressLine4() );
        staffEntity.setAddressLine5( staffDTO.getAddressLine5() );
        staffEntity.setContactNo( staffDTO.getContactNo() );
        staffEntity.setEmail( staffDTO.getEmail() );
        staffEntity.setRole( staffDTO.getRole() );
        staffEntity.setFields( fieldDTOListToFieldEntityList( staffDTO.getFields() ) );
        staffEntity.setVehicles( vehicleDtoListToVehicleEntityList( staffDTO.getVehicles() ) );

        return staffEntity;
    }
}
