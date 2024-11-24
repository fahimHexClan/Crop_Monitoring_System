package lk.ijse.Crop_monitoring_system.Controller;

import lk.ijse.Crop_monitoring_system.Dto.CropDTO;
import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.CropStatus;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Service.CropService;
import lk.ijse.Crop_monitoring_system.util.AppUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/Crop")
@CrossOrigin
public class CropController {
    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(@RequestParam("cropCode") Long cropCode, @RequestParam("commonName") String commonName, @RequestParam("scientificName") String scientificName, @RequestParam("cropImage") MultipartFile cropImage, @RequestParam("category") String category, @RequestParam("season") String season, @RequestParam(value = "Crop_Field", required = false) Long fieldId) {
        try {
            // Convert image file to Base64 string
            String base64Image = AppUtill.ImageToBase64(cropImage.getBytes());

            // Create and set CropDTO
            CropDTO cropDto = new CropDTO();
            cropDto.setCropCode(cropCode);
            cropDto.setCommonName(commonName);
            cropDto.setScientificName(scientificName);
            cropDto.setCropImage(base64Image);
            cropDto.setCategory(category);
            cropDto.setSeason(season);
            cropDto.setFieldId(fieldId);  // Set the field ID

            // Save the crop
            cropService.saveCrop(cropDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{CropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("CropCode") Long cropCode) {
        try {

            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{cropCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CropStatus getSelectedCrop(@PathVariable("cropCode") Long cropCode) {

        return cropService.getCrop(cropCode);
    }
    @PutMapping(value = "/{CropCode}")
    public ResponseEntity<Void> updateCrop(@PathVariable("CropCode") Long cropCode,
                                            @RequestBody CropDTO updateCropDTO) {
        try {
            if (cropCode == null || updateCropDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cropService.updateCrop(cropCode, updateCropDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
