package lk.ijse.Crop_monitoring_system.Controller;

import lk.ijse.Crop_monitoring_system.Dto.MonitoringLogDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.MonitoringLogStatus;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Service.MonitorService;
import lk.ijse.Crop_monitoring_system.util.AppUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/Monitor")
@CrossOrigin
public class MonitorLogController {
    @Autowired
    private MonitorService moniteringLogService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLogs(@RequestParam("id") Long logCode,
                                         @RequestParam("logDate") String logDate,
                                         @RequestParam("logDetails") String logDetails,
                                         @RequestParam("observedImage") MultipartFile observedImage ) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date logDate1 = dateFormat.parse(logDate); // Converts to Date
            String base64ProPic = "";

            byte [] bytesProPic = observedImage.getBytes();
            base64ProPic = AppUtill.ImageToBase64(bytesProPic);

            System.out.println(base64ProPic);
            System.out.println(logDate1);
            System.out.println(logCode);
            System.out.println(logDetails);

            MonitoringLogDTO logDto = new MonitoringLogDTO();
            logDto.setId(logCode);
            logDto.setLogDate(logDate1);
            logDto.setLogDetails(logDetails);
            logDto.setObservedImage(base64ProPic);

            moniteringLogService.saveLogs(logDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{logID}",produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringLogStatus getSelectedLogs(@PathVariable ("logID") Long logID){

        return moniteringLogService.getLogs(logID);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDTO> getALlLogs(){
        return moniteringLogService.getAllLogs();
    }


    @DeleteMapping(value = "/{logID}")
    public ResponseEntity<Void> deleteLogs(@PathVariable ("logID") Long logID){
        try {
            moniteringLogService.deleteLogs(logID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{logID}")
    public ResponseEntity<Void> updatedLogs(@PathVariable ("logID") Long logID,

                                            @RequestParam("logDate") String logDate,
                                            @RequestParam("logDetails") String logDetails,
                                            @RequestParam("observedImage") MultipartFile observedImage){
        //validations
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date logDate1 = dateFormat.parse(logDate); // Converts to Date
            String base64ProPic = "";

            byte [] bytesProPic = observedImage.getBytes();
            base64ProPic = AppUtill.ImageToBase64(bytesProPic);



            MonitoringLogDTO logDto = new MonitoringLogDTO();

            logDto.setLogDate(logDate1);
            logDto.setLogDetails(logDetails);
            logDto.setObservedImage(base64ProPic);

            moniteringLogService.updatedLogs(logID,logDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/ids")
    public ResponseEntity<List<Long>> getAllMonitorIdsIds() {
        try {
            List<Long> logId = moniteringLogService.getAllLogIds();
            return new ResponseEntity<>(logId, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
