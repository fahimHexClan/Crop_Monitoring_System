package lk.ijse.Crop_monitoring_system.Service;

import lk.ijse.Crop_monitoring_system.Dto.StaffDTO;

public interface StaffService {

    void updateStaff(Long staffCode, StaffDTO updatedStaffDTO);

    void saveStaff(StaffDTO staffDTO);
}
