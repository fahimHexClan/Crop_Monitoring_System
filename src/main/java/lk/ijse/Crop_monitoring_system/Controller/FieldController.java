package lk.ijse.Crop_monitoring_system.Controller;

import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Service.FieldService;
import lk.ijse.Crop_monitoring_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController//Http request response handle karanna ena request json walta json walta convort karanawa
@RequestMapping("api/v1/field")//map karanna
@CrossOrigin
public class FieldController {

    @Autowired
    private FieldService fieldService;

/*    @PostMapping(path = "/saveField")
    //@RequestBody eken wenne frontend ekedi jason object widihata thama data yawanne
    //ajson object eke thiyna eka java object widihata harawaganna meka use karanawa
    public SaveFieldRequestDto addField(@RequestBody SaveFieldRequestDto saveFieldRequestDto) {
        return fieldService.addField(saveFieldRequestDto);
    }*/

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveFIeld(@RequestBody FieldDTO fieldDto) {
        String id = fieldService.Save_Fields(fieldDto);


        //return type eka ResponseEntity dila genaric eke api create karapu StandardResponseclass eka dala
        // a class eke attributes tika fill karala okkoma create unama created kiyana eka danawa
        return new ResponseEntity<StandardResponse>(new StandardResponse(201, id + "Item Succesfully saved  : ", id), HttpStatus.CREATED//data ekak aluthen create karama create kiyala return karanawa
        );
    }

}
