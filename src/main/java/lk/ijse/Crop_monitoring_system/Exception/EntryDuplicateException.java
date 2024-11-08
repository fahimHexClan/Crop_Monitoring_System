package lk.ijse.Crop_monitoring_system.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class EntryDuplicateException extends RuntimeException {
    public EntryDuplicateException(String message) {
        super(message);
    }
}
