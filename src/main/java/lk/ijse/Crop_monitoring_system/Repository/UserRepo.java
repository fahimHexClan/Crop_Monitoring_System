package lk.ijse.Crop_monitoring_system.Repository;

import lk.ijse.Crop_monitoring_system.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, String> {
}
