package lk.ijse.Crop_monitoring_system.Dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class StandardResponse {
    private int code;
    private String message;
    private Object data;

//me data 3 front end ekata yanawa
}
