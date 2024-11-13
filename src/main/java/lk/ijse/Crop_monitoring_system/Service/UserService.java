package lk.ijse.Crop_monitoring_system.Service;

import lk.ijse.Crop_monitoring_system.Dto.UserDTO;

public interface UserService {
    String addUser(UserDTO userDTO);

    String updateUser(UserDTO userDTO);

    UserDTO getUserByEmail(String email);

    String deleteEmail(String email);
}
