package lk.ijse.Crop_monitoring_system.Controller;

import lk.ijse.Crop_monitoring_system.Dto.VehicleDto;
import lk.ijse.Crop_monitoring_system.Service.VehicleService;
import lk.ijse.Crop_monitoring_system.Dto.ResponseDto.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/Vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping(path = "/saveVehicle")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody VehicleDto vehicleDto) {

        try {
            String id = vehicleService.addVehicle(vehicleDto);


            if (id.equals("00")) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(201, id + "Vehicle Succesfully saved  : ", id), HttpStatus.CREATED//data ekak aluthen create karama create kiyala return karanawa
                );//return type eka ResponseEntity dila genaric eke api create karapu StandardResponseclass eka dala
                // a class eke attributes tika fill karala okkoma create unama created kiyana eka danawa


            } else if (id.equals("06")) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(401, id + "Vehicle Not saved  : ", id), HttpStatus.BAD_REQUEST//data ekak aluthen create karama create kiyala return karanawa
                );
            } else  {
                return new ResponseEntity<StandardResponse>(new StandardResponse(501, id + " Error  : ", id), HttpStatus.BAD_REQUEST//data ekak aluthen create karama create kiyala return karanawa
                );
            }
        } catch (Exception e) {

            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR  );//data ekak aluthen create karama create kiyala return karanawa

        }

    }











}





