package pers.jason.spiderweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jason.spiderweb.entity.dto.Place;
import pers.jason.spiderweb.entity.dto.TrainStation;
import pers.jason.spiderweb.entity.dto.Vehicle;
import pers.jason.spiderweb.web.request.VehicleStationsRequest;
import pers.jason.spiderweb.web.response.SimpleResponse;
import pers.jason.spiderweb.web.service.VehicleService;

import java.util.ArrayList;
import java.util.List;

import static pers.jason.spiderweb.entity.dto.Place.PlaceType.RAILWAY_STATION;

@RestController
@RequestMapping("vehicle")
public class VehicleController {

  @Autowired
  private VehicleService vehicleService;

  @GetMapping("enter")
  public SimpleResponse enterVehicle(VehicleStationsRequest vehicleStationsRequest) {
    TrainStation station1 = new TrainStation("昆山南", RAILWAY_STATION.code, "09:25", "09:27", 0.0, 0.0);
    TrainStation station2 = new TrainStation("常州北", RAILWAY_STATION.code, "10:02", "10:04", 0.0, 0.0);
    TrainStation station3 = new TrainStation("蚌埠南", RAILWAY_STATION.code, "11:25", "11:27", 0.0, 0.0);
    TrainStation station4 = new TrainStation("宿州东", RAILWAY_STATION.code, "11:49", "11:55", 0.0, 0.0);
    TrainStation station5 = new TrainStation("徐州东", RAILWAY_STATION.code, "12:14", "12:19", 0.0, 0.0);
    TrainStation station6 = new TrainStation("济南西", RAILWAY_STATION.code, "13:22", "13:24", 0.0, 0.0);
    TrainStation station7 = new TrainStation("唐山", RAILWAY_STATION.code, "15:19", "15:22", 0.0, 0.0);
    TrainStation station8 = new TrainStation("秦皇岛", RAILWAY_STATION.code, "15:59", "16:04", 0.0, 0.0);

    TrainStation station9 = new TrainStation("山海关", RAILWAY_STATION.code, "16:18", "16:25", 0.0, 0.0);
    TrainStation station10 = new TrainStation("昌图西", RAILWAY_STATION.code, "19:15", "19:16", 0.0, 0.0);
    TrainStation station11 = new TrainStation("公主岭南", RAILWAY_STATION.code, "19:48", "19:49", 0.0, 0.0);
    TrainStation station12 = new TrainStation("长春", RAILWAY_STATION.code, "20:10", "20:13", 0.0, 0.0);

    TrainStation station13 = new TrainStation("上海虹桥", RAILWAY_STATION.code, null, "10:36", 0.0, 0.0);
    TrainStation station14 = new TrainStation("吉林", RAILWAY_STATION.code, "20:54", null, 0.0, 0.0);
    TrainStation station15 = new TrainStation("沈阳北", RAILWAY_STATION.code, "18:36", "18:42", 0.0, 0.0);
    TrainStation station16 = new TrainStation("南京南", RAILWAY_STATION.code, "10:37", "10:42", 0.0, 0.0);
    TrainStation station17 = new TrainStation("四平东", RAILWAY_STATION.code, "19:32", "19:33", 0.0, 0.0);

    TrainStation station18 = new TrainStation("无锡东", RAILWAY_STATION.code, "09:43", "09:45", 0.0, 0.0);
    TrainStation station19 = new TrainStation("天津西", RAILWAY_STATION.code, "14:34", "14:42", 0.0, 0.0);

    List<TrainStation> stationList = new ArrayList<>();
    stationList.add(station1);
    stationList.add(station2);
    stationList.add(station3);
    stationList.add(station4);
    stationList.add(station5);
    stationList.add(station6);
    stationList.add(station7);
    stationList.add(station8);
    stationList.add(station9);
    stationList.add(station10);
    stationList.add(station11);
    stationList.add(station12);

    stationList.add(station13);
    stationList.add(station14);
    stationList.add(station15);
    stationList.add(station16);
    stationList.add(station17);
    stationList.add(station18);
    stationList.add(station19);

    Vehicle vehicle = new Vehicle();
    vehicle.setType(Vehicle.VehicleType.TRAIN.code);
    vehicle.setCode("G1236");
    return vehicleService.enterVehicle(vehicle, stationList);
  }
}
