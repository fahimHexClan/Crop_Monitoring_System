package lk.ijse.Crop_monitoring_system.Controller;

import lk.ijse.Crop_monitoring_system.Dto.CropDTO;
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

import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
@CrossOrigin
public class CropController {
    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> saveCrop(@RequestParam("cropCode") Long cropCode,
                                         @RequestParam("commonName") String commonName,
                                         @RequestParam("scientificName") String scientificName,
                                         @RequestParam("cropImage") MultipartFile cropImage,
                                         @RequestParam("category") String category,
                                         @RequestParam("cropSeason") String season,
                                         @RequestParam("fieldId") Long fieldId ,
                                         @RequestParam("logId") Long logId) {
        try {
            String base64ProPic = "";

            byte [] bytesProPic = cropImage.getBytes();
            base64ProPic = AppUtill.ImageToBase64(bytesProPic);

            CropDTO cropDto = new CropDTO();
            cropDto.setCropCode(cropCode);
            cropDto.setCommonName(commonName);
            cropDto.setScientificName(scientificName);
            cropDto.setCropImage(base64ProPic);
            cropDto.setCategory(category);
            cropDto.setSeason(season);
            cropDto.setFieldId(fieldId);
            cropDto.setLogId(logId);



            cropService.saveCrop(cropDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{cropId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropStatus getSelectedCrop(@PathVariable ("cropId") Long cropId){
        return cropService.getCrop(cropId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getALlCrops(){
        return cropService.getAllCrop();
    }
    @DeleteMapping(value = "/{cropId}")
    public ResponseEntity<Void> deleteCrop(@PathVariable ("cropId") Long cropId){
        try {

            cropService.deleteCrop(cropId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{cropId}")
    public ResponseEntity<Void> updateNote(@PathVariable ("cropId") Long cropId,
                                           @RequestBody CropDTO updatedCropDto){
        //validations
        try {

                if(cropId==null || updatedCropDto == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cropService.updateCrop(cropId,updatedCropDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}