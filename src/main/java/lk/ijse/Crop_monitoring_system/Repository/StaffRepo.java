package lk.ijse.Crop_monitoring_system.Repository;

import lk.ijse.Crop_monitoring_system.Entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepo extends JpaRepository<StaffEntity,Long> {
    Optional<StaffEntity> findByEmail(String email);
}
