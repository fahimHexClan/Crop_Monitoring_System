package lk.ijse.Crop_monitoring_system.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
//exeption allanne extend karala thyna ekn
public class NotFoundException extends RuntimeException{

    public NotFoundException(String messege){
       //super eken wenne extend wela thiyana class ekata parameter eka aran yanawa
        super(messege);

    }
}
