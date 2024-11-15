package lk.ijse.Crop_monitoring_system.Dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lk.ijse.Crop_monitoring_system.Dto.Status.LocationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class LocationDTO implements LocationStatus {

        private Double latitude;
        private Double longitude;

        @JsonCreator
        public LocationDTO(@JsonProperty("latitude") Double latitude,
                           @JsonProperty("longitude") Double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
        // getters and setters
    }


