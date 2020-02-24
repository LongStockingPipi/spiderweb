package pers.jason.spiderweb.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.jason.spiderweb.entity.dto.Place;
import pers.jason.spiderweb.entity.dto.Vehicle;

@Mapper
public interface VehicleDao {

  Vehicle findVehicleById(@Param("id") Long id);

  Vehicle findVehicleByCode(@Param("code") String code, @Param("type") Integer type);

  int saveVehicle(Vehicle vehicle);

  int updateVehicle(Vehicle vehicle);
}
