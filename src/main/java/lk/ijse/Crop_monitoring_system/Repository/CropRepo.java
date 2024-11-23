package lk.ijse.Crop_monitoring_system.Repository;

import lk.ijse.Crop_monitoring_system.Entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepo extends JpaRepository<CropEntity, Long> {
}
