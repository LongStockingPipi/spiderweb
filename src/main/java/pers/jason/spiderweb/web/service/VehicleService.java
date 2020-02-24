package pers.jason.spiderweb.web.service;

import pers.jason.spiderweb.entity.dto.TimeTable;
import pers.jason.spiderweb.entity.dto.TrainStation;
import pers.jason.spiderweb.entity.dto.Vehicle;
import pers.jason.spiderweb.graph.neo4j.pojo.NodeVehicle;
import pers.jason.spiderweb.web.response.SimpleResponse;

import java.util.List;

public interface VehicleService {

  /**
   * 先从neo4j获取数据，若没有，则从mysql获取，在封装成neo4j数据结构返回
   * @param vehicleId
   * @return
   */
  NodeVehicle findVehicleById(Long vehicleId);

  TimeTable findTimeTableByVehicleIdAndPlaceId(Long vehicleId, Long placeId);

  SimpleResponse enterVehicle(Vehicle vehicle, List<TrainStation> stations);
}
