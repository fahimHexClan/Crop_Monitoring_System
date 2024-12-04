package lk.ijse.Crop_monitoring_system.Controller;

import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.StaffStatus;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/Staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Void> saveStaff(@RequestBody StaffDTO staffDto) {
        try {
            //System.out.println(staffDto);
            staffService.saveStaff(staffDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{staffCode}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStaff(@PathVariable("staffCode") Long staffCode, @RequestBody StaffDTO updatedStaffDTO) {
        try {
            if (staffCode == null || updatedStaffDTO == null) {
                return new ResponseEntity<>("Invalid input data", HttpStatus.BAD_REQUEST);
            }
            staffService.updateStaff(staffCode, updatedStaffDTO);
            return new ResponseEntity<>("Staff member updated successfully", HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable ("staffId") Long staffId){
        try {
            staffService.deleteStaff(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{staffId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffStatus getSelectedStaff(@PathVariable ("staffId") Long staffId){
        return staffService.getStaff(staffId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getALlStaff(){
        return staffService.getAllStaff();
    }

  /*  @GetMapping(value = "/ids", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Long>> getAllStaffIds() {
        List<Long> staffIds = staffService.getAllStaffIds();
        return new ResponseEntity<>(staffIds, HttpStatus.OK);
    }*/

}