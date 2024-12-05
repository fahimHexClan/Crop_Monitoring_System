package lk.ijse.Crop_monitoring_system.Controller;

import lk.ijse.Crop_monitoring_system.Dto.EquipmentDTO;
import lk.ijse.Crop_monitoring_system.Dto.ResponseDto.StandardResponse;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/Equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping(path = "/saveEquipment")
    public ResponseEntity<StandardResponse> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {

        try {
            String id = equipmentService.addEquipment(equipmentDTO);
            if (id.equals("00")) {//VarList eke aapu data tika thama mekata dala thiyanne

                return new ResponseEntity<StandardResponse>(new StandardResponse(201, id + "  Equipment Succesfully saved  : ", equipmentDTO), HttpStatus.CREATED//data ekak aluthen create karama create kiyala return karanawa
                );//return type eka ResponseEntity dila genaric eke api create karapu StandardResponseclass eka dala
                // a class eke attributes tika fill karala okkoma create unama created kiyana eka danawa
            } else if (id.equals("06")) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(401, id + "  Equipment Not saved  : ", id), HttpStatus.BAD_REQUEST//data ekak aluthen create karama create kiyala return karanawa
                );
            } else {
                return new ResponseEntity<StandardResponse>(new StandardResponse(501, id + " Error  : ", id), HttpStatus.BAD_REQUEST//data ekak aluthen create karama create kiyala return karanawa
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);//data ekak aluthen create karama create kiyala return karanawa
        }
    }


    @PutMapping(path = "/updateEquipment")
    public ResponseEntity<StandardResponse> updateEquipment(@RequestBody EquipmentDTO equipmentDTO) {

        try {
            String id = equipmentService.updateEquipment(equipmentDTO);
            if (id.equals("00")) {//VarList eke aapu data tika thama mekata dala thiyanne

                return new ResponseEntity<StandardResponse>(new StandardResponse(201, id + "  Equipment Succesfully update  : ", equipmentDTO), HttpStatus.CREATED//data ekak aluthen create karama create kiyala return karanawa
                );//return type eka ResponseEntity dila genaric eke api create karapu StandardResponseclass eka dala
                // a class eke attributes tika fill karala okkoma create unama created kiyana eka danawa
            } else if (id.equals("01")) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(401, id + "  Equipment Not update  : ", id), HttpStatus.BAD_REQUEST//data ekak aluthen create karama create kiyala return karanawa
                );
            } else {
                return new ResponseEntity<StandardResponse>(new StandardResponse(501, id + " Error  : ", id), HttpStatus.BAD_REQUEST//data ekak aluthen create karama create kiyala return karanawa
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);//data ekak aluthen create karama create kiyala return karanawa
        }
    }

    @GetMapping(path = {"/get_All_Equipment"})
    public ResponseEntity<StandardResponse> getAllEquipment() {
        try {
            List<EquipmentDTO> allEquipments = equipmentService.getAllEquipments();
            return new ResponseEntity<StandardResponse>(new StandardResponse(200, " Succesfully get Equipments ", allEquipments), HttpStatus.ACCEPTED//data ekak aluthen get karama ok kiyala return karanawa
            );

        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);//data ekak aluthen create karama create kiyala return karanawa

        }

    }

    @GetMapping(path = "/Search_Equipment_By_Id/{equipmentId}")
    public ResponseEntity<StandardResponse> findEquipmentByEquipmentId(@PathVariable("equipmentId") Long equipmentId) {
        try {
            EquipmentDTO equipmentDTO = equipmentService.getEquipmentById(equipmentId);
            if (equipmentDTO != null) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(200, "   Succesfully get Equipment by Id  ", equipmentDTO), HttpStatus.ACCEPTED);//data ekak aluthen get karama ok kiyala return karanawa

            } else {
                return new ResponseEntity<StandardResponse>(new StandardResponse(600, equipmentId + "  No Equipment is avilable on this id  : ", equipmentId), HttpStatus.BAD_REQUEST);//data ekak aluthen create karama create kiyala return karanawa


            }
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);//data ekak aluthen create karama create kiyala return karanawa

        }
    }

    @DeleteMapping("/deleteEquipment/{equipmentID}")
    public ResponseEntity<StandardResponse> deleteVehicle(@PathVariable("equipmentID") Long equipmentID) {
        try {
            String res = equipmentService.deleteEquipment(equipmentID);
            if (res.equals("00")) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(200, "  Equipment Succesfully delete by Id  ", equipmentID), HttpStatus.ACCEPTED);//data ekak aluthen get karama ok kiyala return karanawa

            } else {
                return new ResponseEntity<StandardResponse>(new StandardResponse(600, equipmentID + "  No Equipment is avilable on this id  : ", equipmentID), HttpStatus.BAD_REQUEST);//data ekak aluthen create karama create kiyala return karanawa

            }
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);//data ekak aluthen create karama create kiyala return karanawa
        }
    }
    @GetMapping(path = "/ids", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> getAllEquipmentIds() {
        try {
            List<Long> equipmentIds = equipmentService.getAllEquipmentIds();
            return new ResponseEntity<>(
                    new StandardResponse(200, "Successfully Retrieved Equipment IDs", equipmentIds),
                    HttpStatus.OK
            );
        } catch (DataPersistException e) {
            return new ResponseEntity<>(
                    new StandardResponse(400, "Failed to Retrieve Equipment IDs", null),
                    HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new StandardResponse(500, "Internal Server Error", null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


}
