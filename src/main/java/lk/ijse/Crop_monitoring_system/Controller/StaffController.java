package lk.ijse.Crop_monitoring_system.Controller;

import lk.ijse.Crop_monitoring_system.Dto.EquipmentDTO;
import lk.ijse.Crop_monitoring_system.Dto.ResponseDto.StandardResponse;
import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/Staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping(path = "/saveStaff",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> saveStaff(@RequestBody StaffDTO staffDTO) {

        try {
            String id = staffService.saveStaff(staffDTO);
            if (id.equals("00")) {//VarList eke aapu data tika thama mekata dala thiyanne

                return new ResponseEntity<StandardResponse>(new StandardResponse(201, id + "  Staff Succesfully saved  : ", id), HttpStatus.CREATED//data ekak aluthen create karama create kiyala return karanawa
                );//return type eka ResponseEntity dila genaric eke api create karapu StandardResponseclass eka dala
                // a class eke attributes tika fill karala okkoma create unama created kiyana eka danawa
            } else if (id.equals("06")) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(401, id + "  Staff Not saved  : ", id), HttpStatus.BAD_REQUEST//data ekak aluthen create karama create kiyala return karanawa
                );
            } else {
                return new ResponseEntity<StandardResponse>(new StandardResponse(501, id + " Error  : ", id), HttpStatus.BAD_REQUEST//data ekak aluthen create karama create kiyala return karanawa
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);//data ekak aluthen create karama create kiyala return karanawa
        }
    }

}

