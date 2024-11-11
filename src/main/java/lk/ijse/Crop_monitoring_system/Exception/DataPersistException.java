package lk.ijse.Crop_monitoring_system.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class DataPersistException extends RuntimeException {
    public DataPersistException() {
    }

    public DataPersistException(String message) {

    }
    public DataPersistException(String message, Throwable cause) {

    }
}