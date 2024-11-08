package lk.ijse.Crop_monitoring_system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CropMonitoringSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CropMonitoringSystemApplication.class, args);
    }
    @Bean//module mapper eka hadanna dependancy , Bean annotation eka dala ita passe me method eka dala return
    //karala dependancy injectoin ekak widihata use karanawa
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
