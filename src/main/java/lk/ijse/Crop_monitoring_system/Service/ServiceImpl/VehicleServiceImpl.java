package lk.ijse.Crop_monitoring_system.Service.ServiceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.Crop_monitoring_system.Dto.VehicleDto;
import lk.ijse.Crop_monitoring_system.Entity.VehicleEntity;
import lk.ijse.Crop_monitoring_system.Exception.DataPersistException;
import lk.ijse.Crop_monitoring_system.Repository.VehicleRepo;
import lk.ijse.Crop_monitoring_system.Service.VehicleService;
import lk.ijse.Crop_monitoring_system.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public String addVehicle(VehicleDto vehicleDto) {
        if (vehicleRepo.existsById(vehicleDto.getId())) {
            return VarList.RSP_DUPLICATED;
        } else {
            vehicleRepo.save(modelMapper.map(vehicleDto, VehicleEntity.class));
            return VarList.RSP_SUCCESS;
        }
    }

    @Override
    public String updateVehicle(VehicleDto vehicleDto) {
        if (vehicleRepo.existsById(vehicleDto.getId())) {
            vehicleRepo.save(modelMapper.map(vehicleDto, VehicleEntity.class));
            return VarList.RSP_SUCCESS;
        } else {

            return VarList.RSP_DUPLICATED;
        }
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        List<VehicleEntity> vehicleEntityList = vehicleRepo.findAll();

        return modelMapper.map(vehicleEntityList, new TypeToken<ArrayList<VehicleDto>>() {

        }.getType());
    }

    @Override
    public VehicleDto getVehicleById(Long vehicleId) {
        if (vehicleRepo.existsById(vehicleId)) {
            VehicleEntity vehicleEntity = vehicleRepo.findById(vehicleId).orElse(null);
            return modelMapper.map(vehicleEntity, VehicleDto.class);
        } else {
            return null;
        }
    }

    @Override
    public String deleteVehicle(Long vehicleID) {
        if (vehicleRepo.existsById(vehicleID)) {
            vehicleRepo.deleteById(vehicleID);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_DUPLICATED;
        }
    }

    @Override
    public List<Long> getAllVehicleIds() {
        try {
            List<VehicleEntity> vehicles = vehicleRepo.findAll();
            return vehicles.stream()
                    .map(VehicleEntity::getId)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DataPersistException("Failed to retrieve vehicle IDs", e);
        }
    }
}


