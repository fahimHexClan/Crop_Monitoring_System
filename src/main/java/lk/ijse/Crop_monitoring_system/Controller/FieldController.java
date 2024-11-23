package lk.ijse.Crop_monitoring_system.Controller;

import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;

import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Service.FieldServise;
import lk.ijse.Crop_monitoring_system.util.AppUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/field")
@CrossOrigin
public class FieldController {
    @Autowired
    private FieldServise fieldServise;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestParam("fieldCode") Long fieldCode,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocation[x]") int x,
            @RequestParam("fieldLocation[y]") int y,
            @RequestParam("extent_size") Double extentSize,
            @RequestParam("fieldImageOne") MultipartFile fieldImageOne,
            @RequestParam("fieldImageTwo") MultipartFile fieldImageTwo,
            @RequestParam(value = "Field_Staff", required = false) List<Long> fieldStaffIds)
            {
        try {
            Point location = new Point(x, y);
            // Convert image files to Base64 strings
            String base64ImageOne = AppUtill.ImageToBase64(fieldImageOne.getBytes());
            String base64ImageTwo = AppUtill.ImageToBase64(fieldImageTwo.getBytes());

            // Prepare StaffDto list
            List<StaffDTO> staffDtos = new ArrayList<>();
            if (fieldStaffIds != null && !fieldStaffIds.isEmpty()) {
                for (Long staffId : fieldStaffIds) {
                    StaffDTO staffDto = new StaffDTO();
                    staffDto.setId(staffId);
                    staffDtos.add(staffDto);
                }
            }


            // Create and set FieldDto
            FieldDTO fieldDto = new FieldDTO();
            fieldDto.setFieldId(fieldCode);
            fieldDto.setFieldName(fieldName);
            fieldDto.setFieldLocation(location);
            fieldDto.setExtentSize(extentSize);
            fieldDto.setFieldImage1(base64ImageOne);
            fieldDto.setFieldImage2(base64ImageTwo);
           // fieldDto.setStaff(staffDtos);

            // Save the field
            System.out.println(fieldDto.toString()) ;
            fieldServise.SaveField(fieldDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{FieldCode}")
    public ResponseEntity<Void> updateField(@PathVariable("FieldCode") Long fieldCode,
                                            @RequestBody FieldDTO updatedFieldDTO) {
        try {
            if (fieldCode == null || updatedFieldDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldServise.updateField(fieldCode, updatedFieldDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{FieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("FieldCode") Long fieldCode) {
        try {

            fieldServise.deletefield(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}




