package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.FieldDto;
import lk.ijse.Crop_monitoring_system.Dto.Request.SaveFieldRequestDto;
import lk.ijse.Crop_monitoring_system.Entity.FieldEntity;
import lk.ijse.Crop_monitoring_system.Exception.EntryDuplicateException;
import lk.ijse.Crop_monitoring_system.Repository.FieldRepo;
import lk.ijse.Crop_monitoring_system.Service.FieldService;
import lk.ijse.Crop_monitoring_system.util.Mappers.FieldMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service//meka danne service layer eka identify karaganna
@Transactional //data 2 tables walta eka paara yawanna

public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepo fieldRepo;

    @Autowired
    private FieldMapper fieldMapper;//mapstruck usre karanne Queries ghaia use karnna

    @Autowired
    private ModelMapper modelMapper;//Dto ekai Entity ekai map karaganna use karanne



    @Override
    public String Save_Fields(FieldDto fieldDto) {
        FieldEntity field = fieldMapper.RequesDtoToEntity(fieldDto);

        if (!fieldRepo.existsById(field.getFieldId())) {

            return fieldRepo.save(field).getFieldName();
        } else {
            throw new EntryDuplicateException("Allredy duplicate entry");
        }
    }



}