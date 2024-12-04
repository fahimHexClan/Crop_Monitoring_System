package lk.ijse.Crop_monitoring_system.Service;

import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;
import lk.ijse.Crop_monitoring_system.Dto.Status.StaffStatus;

import java.util.List;

public interface StaffService {

    void updateStaff(Long staffCode, StaffDTO updatedStaffDTO);

    void saveStaff(StaffDTO staffDTO);

    void deleteStaff(Long staffId);

    StaffStatus getStaff(Long staffId);

    List<StaffDTO> getAllStaff();


}
