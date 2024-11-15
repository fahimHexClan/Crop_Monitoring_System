package lk.ijse.Crop_monitoring_system.Controller;

import lk.ijse.Crop_monitoring_system.Dto.ResponseDto.StandardResponse;
import lk.ijse.Crop_monitoring_system.Dto.UserDTO;
import lk.ijse.Crop_monitoring_system.Dto.VehicleDto;
import lk.ijse.Crop_monitoring_system.Service.UserService;
import lk.ijse.Crop_monitoring_system.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/User")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/saveUser")
    public ResponseEntity<StandardResponse> saveUser(@RequestBody UserDTO userDTO) {

        try {
            String email = userService.addUser(userDTO);
            if (email.equals("00")) {//VarList eke aapu data tika thama mekata dala thiyanne

                return new ResponseEntity<StandardResponse>(new StandardResponse(201, email + "User Succesfully saved  : ", email), HttpStatus.CREATED//data ekak aluthen create karama create kiyala return karanawa
                );//return type eka ResponseEntity dila genaric eke api create karapu StandardResponseclass eka dala
                // a class eke attributes tika fill karala okkoma create unama created kiyana eka danawa
            } else if (email.equals("06")) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(401, email + "User Not saved  : ", email), HttpStatus.BAD_REQUEST//data ekak aluthen create karama create kiyala return karanawa
                );
            } else {
                return new ResponseEntity<StandardResponse>(new StandardResponse(501, email + " Error  : ", email), HttpStatus.BAD_REQUEST//data ekak aluthen create karama create kiyala return karanawa
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);//data ekak aluthen create karama create kiyala return karanawa
        }
    }

    @PutMapping(path = "/updateUser")
    public ResponseEntity<StandardResponse> updateUser(@RequestBody UserDTO userDTO) {

        try {
            String email = userService.updateUser(userDTO);
            if (email.equals("00")) {//VarList eke aapu data tika thama mekata dala thiyanne

                return new ResponseEntity<StandardResponse>(new StandardResponse(201, email + "User Succesfully update  : ", email), HttpStatus.CREATED//data ekak aluthen create karama create kiyala return karanawa
                );//return type eka ResponseEntity dila genaric eke api create karapu StandardResponseclass eka dala
                // a class eke attributes tika fill karala okkoma create unama created kiyana eka danawa
            } else if (email.equals("01")) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(401, email + "User Not update  : ", email), HttpStatus.BAD_REQUEST//data ekak aluthen create karama create kiyala return karanawa
                );
            } else {
                return new ResponseEntity<StandardResponse>(new StandardResponse(501, email + " Error  : ", email), HttpStatus.BAD_REQUEST//data ekak aluthen create karama create kiyala return karanawa
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);//data ekak aluthen create karama create kiyala return karanawa
        }
    }

    @GetMapping(path = "/Search_User_By_Email/{email}")
    public ResponseEntity<StandardResponse> findVehicleByVehicleId(@PathVariable("email") String email) {
        try {
            UserDTO userDTO= userService.getUserByEmail(email);
            if (userDTO != null) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(200, " Succesfully get by email  ", email), HttpStatus.ACCEPTED);//data ekak aluthen get karama ok kiyala return karanawa

            }else {
                return new ResponseEntity<StandardResponse>(new StandardResponse(600, email + "  No User is avilable on this email  : ", email), HttpStatus.BAD_REQUEST);//data ekak aluthen create karama create kiyala return karanawa


            }
        }catch (Exception e){
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);//data ekak aluthen create karama create kiyala return karanawa

        }
    }

    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<StandardResponse> deleteVehicle(@PathVariable("email") String email) {
        try {
            String res = userService.deleteEmail(email);
            if (res.equals("00")) {
                return new ResponseEntity<StandardResponse>(new StandardResponse(200, " Succesfully delete by user  ", email), HttpStatus.ACCEPTED);//data ekak aluthen get karama ok kiyala return karanawa

            } else {
                return new ResponseEntity<StandardResponse>(new StandardResponse(600, email + "  No user is avilable on this id  : ", email), HttpStatus.BAD_REQUEST);//data ekak aluthen create karama create kiyala return karanawa

            }
        }catch (Exception e){
            return new ResponseEntity<StandardResponse>(new StandardResponse(601, " Error  : ", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);//data ekak aluthen create karama create kiyala return karanawa
        }
    }

}