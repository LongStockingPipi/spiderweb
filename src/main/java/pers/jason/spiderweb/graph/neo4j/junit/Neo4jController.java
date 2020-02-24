package pers.jason.spiderweb.graph.neo4j.junit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jason.spiderweb.entity.dto.Place;
import pers.jason.spiderweb.entity.dto.Vehicle;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePlace;
import pers.jason.spiderweb.graph.neo4j.pojo.NodeVehicle;
import pers.jason.spiderweb.graph.neo4j.pojo.Via;
import pers.jason.spiderweb.graph.neo4j.repositories.VehicleRepository;

@RestController
@RequestMapping("neo4j")
public class Neo4jController {

  @Autowired
  private VehicleRepository vehicleRepository;

  @GetMapping("vehicle")
  public void savePlaceAndVehicle() {
    NodeVehicle vehicle = new NodeVehicle();
    vehicle.setCode("G1236");
    vehicle.setId(1L);
    vehicle.setType(Vehicle.VehicleType.TRAIN.code);
    vehicle.setDepartureStation("上海虹桥");
    vehicle.setDestinationStation("吉林");

    NodePlace from = new NodePlace();
    from.setId(1L);
    from.setName("上海虹桥");
    from.setX(0.0);
    from.setY(0.0);
    from.addLabels(Place.PlaceType.RAILWAY_STATION.name);


    NodePlace to = new NodePlace();
    to.setId(2L);
    to.setName("吉林");
    to.setX(0.0);
    to.setY(0.0);
    to.addLabels(Place.PlaceType.RAILWAY_STATION.name);

    //虹桥
    Via via1 = new Via();
    via1.setSetOutTime("09:08:00");
    via1.setNum(1);
    via1.setPlace(from);
    via1.setVehicle(vehicle);

    Via via2 = new Via();
    via2.setArrivalTime("20:54:00");
    via2.setNum(19);
    via2.setPlace(to);
    via2.setVehicle(vehicle);

    vehicle.getStations().add(via1);
    vehicle.getStations().add(via2);


//    vehicle.setGraphId(0L);
//    via1.setGraphId(20L);
//    via2.setGraphId(21L);
//    from.setGraphId(20L);
//    to.setGraphId(130L);

//    from.setName("上海虹桥A");

    vehicle = vehicleRepository.save(vehicle);

    System.out.println(vehicle);
  }
}
