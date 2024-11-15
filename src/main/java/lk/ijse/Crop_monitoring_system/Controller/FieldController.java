package lk.ijse.Crop_monitoring_system.Controller;

import lk.ijse.Crop_monitoring_system.Dto.FieldDTO;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Exception.NoteNotFoundException;
import lk.ijse.Crop_monitoring_system.Service.FieldServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {
    @Autowired
    private FieldServise fieldServise;

    @PostMapping(path = ("/saveField"),consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveNote(@RequestBody FieldDTO fieldDTO) {
        try {
            fieldServise.saveField(fieldDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving field: " + e.getMessage());
        }
    }


        /*try {
            fieldServise.saveField(fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


    @GetMapping(path = ("/getAllField"),produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getALlNotes(){
        return fieldServise.getAllField();
    }


    @DeleteMapping(path = ("/deleteField/{fieldId}"))
    public ResponseEntity<Void> deleteNote(@PathVariable ("fieldId") Long fieldId){
        try {
            fieldServise.deletefield(fieldId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoteNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(path = ("/updateField/{fieldId}"))
    public ResponseEntity<Void> updateNote(@PathVariable ("fieldId") Long fieldId,
                                           @RequestBody FieldDTO fieldDTO){
        //validations
        try {
            fieldServise.updateField(fieldId,fieldDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoteNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}