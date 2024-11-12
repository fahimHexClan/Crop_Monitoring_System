package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.UserDTO;
import lk.ijse.Crop_monitoring_system.Entity.UserEntity;
import lk.ijse.Crop_monitoring_system.Entity.VehicleEntity;
import lk.ijse.Crop_monitoring_system.Repository.UserRepo;
import lk.ijse.Crop_monitoring_system.Service.UserService;
import lk.ijse.Crop_monitoring_system.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addUser(UserDTO userDTO) {
        if (userRepo.existsById(userDTO.getEmail())) {
            return VarList.RSP_DUPLICATED;
        } else {
            userRepo.save(modelMapper.map(userDTO, UserEntity.class));
            return VarList.RSP_SUCCESS;
        }
    }

    @Override
    public String updateUser(UserDTO userDTO) {
        if (userRepo.existsById(userDTO.getEmail())) {
            userRepo.save(modelMapper.map(userDTO, UserEntity.class));
            return VarList.RSP_SUCCESS;
        } else {

            return VarList.RSP_DUPLICATED;
        }
    }
}
