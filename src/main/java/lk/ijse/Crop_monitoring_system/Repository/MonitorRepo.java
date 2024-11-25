package lk.ijse.Crop_monitoring_system.Repository;

import lk.ijse.Crop_monitoring_system.Entity.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorRepo extends JpaRepository<MonitoringLogEntity,Long> {

}
