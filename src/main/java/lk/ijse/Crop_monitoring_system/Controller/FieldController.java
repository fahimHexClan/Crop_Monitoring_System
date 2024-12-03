package lk.ijse.Crop_monitoring_system.Controller;

import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;

import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.FieldStatus;
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
            @RequestParam(value = "Field_Staff", required = false) List<Long> fieldStaffIds,
            @RequestParam(value = "logId", required = false) Long logId
            )
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
                    staffDto.setStaffCode(staffId);
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
            fieldDto.setStaff(staffDtos);
            fieldDto.setLogId(logId);

            // Save the field

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

                                            @RequestParam("fieldName") String fieldName,
                                            @RequestParam("fieldLocation[x]") int x,
                                            @RequestParam("fieldLocation[y]") int y,
                                            @RequestParam("extent_size") Double extentSize,
                                            @RequestParam("fieldImageOne") MultipartFile fieldImageOne,
                                            @RequestParam("fieldImageTwo") MultipartFile fieldImageTwo,
                                            @RequestParam(value = "Field_Staff", required = false) List<Long> fieldStaffIds,
                                            @RequestParam(value = "logId", required = false) Long logId) {
        try {
            // Step 1: Convert location to Point
            Point location = new Point(x, y);

            // Step 2: Convert images to Base64
            String base64ImageOne = AppUtill.ImageToBase64(fieldImageOne.getBytes());
            String base64ImageTwo = AppUtill.ImageToBase64(fieldImageTwo.getBytes());

            // Step 3: Prepare StaffDTO list
            List<StaffDTO> staffDtos = new ArrayList<>();
            if (fieldStaffIds != null && !fieldStaffIds.isEmpty()) {
                for (Long staffId : fieldStaffIds) {
                    StaffDTO staffDto = new StaffDTO();
                    staffDto.setStaffCode(staffId);
                    staffDtos.add(staffDto);
                }
            }

            // Step 4: Create and set updated FieldDTO
            FieldDTO fieldDto = new FieldDTO();
            fieldDto.setFieldId(fieldCode);
            fieldDto.setFieldName(fieldName);
            fieldDto.setFieldLocation(location);
            fieldDto.setExtentSize(extentSize);
            fieldDto.setFieldImage1(base64ImageOne);
            fieldDto.setFieldImage2(base64ImageTwo);
            fieldDto.setStaff(staffDtos);
            fieldDto.setLogId(logId);

            // Step 5: Update the field using the service
            fieldServise.updateField(fieldCode, fieldDto);

            // Return response indicating update success
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

    @GetMapping(value = "/{FieldCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldStatus getSelectedField(@PathVariable("FieldCode") Long fieldCode) {

        return fieldServise.getField(fieldCode);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getALlFields() {
        return fieldServise.getAllField();
    }

    @GetMapping("/ids")
    public ResponseEntity<List<Long>> getAllFieldIds() {
        try {
            List<Long> fieldIds = fieldServise.getAllFieldIds();
            return new ResponseEntity<>(fieldIds, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}




