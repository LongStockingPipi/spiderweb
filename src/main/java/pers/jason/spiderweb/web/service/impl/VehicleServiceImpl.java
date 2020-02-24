package pers.jason.spiderweb.web.service.impl;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pers.jason.spiderweb.entity.dto.Place;
import pers.jason.spiderweb.entity.dto.Place.PlaceType;
import pers.jason.spiderweb.entity.dto.TimeTable;
import pers.jason.spiderweb.entity.dto.TrainStation;
import pers.jason.spiderweb.entity.dto.Vehicle;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePlace;
import pers.jason.spiderweb.graph.neo4j.pojo.NodeVehicle;
import pers.jason.spiderweb.graph.neo4j.pojo.Via;
import pers.jason.spiderweb.graph.neo4j.service.Neo4jService;
import pers.jason.spiderweb.web.dao.VehicleDao;
import pers.jason.spiderweb.web.response.SimpleResponse;
import pers.jason.spiderweb.web.service.PlaceService;
import pers.jason.spiderweb.web.service.TimeTableService;
import pers.jason.spiderweb.web.service.VehicleService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class VehicleServiceImpl implements VehicleService {

  @Autowired
  private Neo4jService neo4jService;

  @Autowired
  private PlaceService placeService;

  @Autowired
  private TimeTableService timeTableService;

  @Autowired
  private VehicleDao vehicleDao;

  @Override
  public TimeTable findTimeTableByVehicleIdAndPlaceId(Long vehicleId, Long placeId) {
    return null;
  }

  @Override
  public NodeVehicle findVehicleById(Long vehicleId) {
    NodeVehicle nodeVehicle = neo4jService.findVehicleBySqlId(vehicleId);
    if(null == nodeVehicle || null == nodeVehicle.getGraphId()) {
      Vehicle vehicle = vehicleDao.findVehicleById(vehicleId);
      nodeVehicle = new NodeVehicle();
      nodeVehicle.setId(vehicleId);
      nodeVehicle.setCode(vehicle.getCode());
      nodeVehicle.setType(vehicle.getType());
      NodePlace to = placeService.findPlaceById(vehicle.getDestinationStation());
      NodePlace from = placeService.findPlaceById(vehicle.getDepartureStation());
      nodeVehicle.setDepartureStation(from.getName());
      nodeVehicle.setDestinationStation(to.getName());
    }
    return nodeVehicle;
  }

  @Override
  @Transactional(rollbackFor = Exception.class, value = "multiTransactionManager")
  public SimpleResponse enterVehicle(Vehicle vehicle, List<TrainStation> stations) {
    Integer type = vehicle.getType();
    String code = vehicle.getCode();
    Vehicle dbVehicle = vehicleDao.findVehicleByCode(code, type);
    if(null != dbVehicle) {
      vehicle = dbVehicle;
    }
    Long departureStation;
    Long destinationStation;
    String departureStationName;
    String destinationStationName;
    List<String> stationNames = new ArrayList<>();
    stations.forEach(station -> stationNames.add(station.getName()));
    PlaceType placeType = placeService.getPlaceTypeByVehicle(type);
    List<Place> places = placeService.findPlacesByName(stationNames, placeType.code);
    List<String> alreadySavedPlaces = new ArrayList<>();
    places.forEach(place -> alreadySavedPlaces.add(place.getName()));

    List<Place> newPlaces = new ArrayList<>();
    stations.forEach(station -> {
      String placeName = station.getName();
      if(!alreadySavedPlaces.contains(placeName)) {
        Place place = new Place();
        place.setName(placeName);
        place.setType(placeType.code);
        place.setX(station.getX());
        place.setY(station.getY());
        newPlaces.add(place);
      }
    });
    placeService.savePlaces(newPlaces);

    newPlaces.forEach(place -> places.add(place));
    Map<String, Place> nameAndStation = new HashMap<>();
    Map<Long, Place> idAndStation = new HashMap<>();
    places.forEach(place -> {
      nameAndStation.put(place.getName(), place);
      idAndStation.put(place.getPlaceId(), place);
    });


    stations.forEach(station -> {
      station.setPlaceId(nameAndStation.get(station.getName()).getPlaceId());
    });
    Collections.sort(stations);
    departureStation = stations.get(0).getPlaceId();
    departureStationName = stations.get(0).getName();
    destinationStation = stations.get(stations.size() - 1).getPlaceId();
    destinationStationName = stations.get(stations.size() - 1).getName();
    vehicle.setDepartureStation(departureStation);
    vehicle.setDestinationStation(destinationStation);
    saveOrUpdateVehicle(vehicle);

    List<TimeTable> timeTables = new ArrayList<>();
    Map<Long, TimeTable> timeTableMap = new HashMap<>();
    List<Long> idList = new ArrayList<>();
    Vehicle finalVehicle = vehicle;
    stations.forEach(station -> {
      Long stationId = station.getPlaceId();
      TimeTable timeTable = new TimeTable();
      timeTable.setPlaceId(stationId);
      timeTable.setVehicleId(finalVehicle.getId());
      timeTable.setArriveTime(station.getStartTime());
      timeTable.setSetOutTime(station.getEndTime());
      timeTable.setPlaceName(station.getName());
      timeTable.setVehicleCode(finalVehicle.getCode());
      timeTables.add(timeTable);
      idList.add(stationId);
      timeTableMap.put(stationId, timeTable);
    });
    timeTableService.save(timeTables);

    //1. vehicle
    //2. place
    //3. via
    List<Place> newNodePlaces = new ArrayList<>();
    final List<NodePlace> existPlacesNoRelationship = new ArrayList<>();
    List<Long> needRemove = new ArrayList<>();

    NodeVehicle nodeVehicle = neo4jService.findVehicleAndPlacesById(vehicle.getId());
    if(null == nodeVehicle) {
      nodeVehicle = new NodeVehicle();
      nodeVehicle.setId(vehicle.getId());
      nodeVehicle.setCode(code);
      nodeVehicle.setType(type);
      nodeVehicle.setDepartureStation(departureStationName);
      nodeVehicle.setDestinationStation(destinationStationName);
    }

    List<NodePlace> nodePlaces = new ArrayList<>();
    if(!CollectionUtils.isEmpty(idList)) {
      nodePlaces = neo4jService.findPlacesById(idList);
    }
    List<NodePlace> existNodePlaces = new ArrayList<>();

    List<Long> existPlaceId = new ArrayList<>();
    nodePlaces.forEach(place -> {
      existPlaceId.add(place.getId());
    });
    idList.forEach(id -> {
      if(!existPlaceId.contains(id)) {
        newNodePlaces.add(idAndStation.get(id));
      }
    });

    Set<Via> viaSet = nodeVehicle.getStations();
    viaSet.forEach(via -> {
      Long placeId = via.getPlace().getId();
      if(!idList.contains(placeId)) {
        needRemove.add(via.getGraphId());
      } else {
        existNodePlaces.add(via.getPlace());
      }
    });

    nodePlaces.forEach(place -> {
      if(!existNodePlaces.contains(place)) {
        existPlacesNoRelationship.add(place);
      }
    });

    NodeVehicle finalNodeVehicle = nodeVehicle;
    existPlacesNoRelationship.forEach(place -> {
      Via via = new Via();
      via.setPlace(place);
      via.setNum(0);
      via.setVehicle(finalNodeVehicle);
      TimeTable timeTable = timeTableMap.get(place.getId());
      via.setArrivalTime(timeTable.getArriveTime());
      via.setSetOutTime(timeTable.getSetOutTime());
      viaSet.add(via);
    });

    newNodePlaces.forEach(place -> {
      Via via = new Via();
      via.setVehicle(finalNodeVehicle);
      via.setNum(0);
      TimeTable timeTable = timeTableMap.get(place.getPlaceId());
      via.setArrivalTime(timeTable.getArriveTime());
      via.setSetOutTime(timeTable.getSetOutTime());
      NodePlace nodePlace = new NodePlace();
      nodePlace.setId(place.getPlaceId());
      nodePlace.setX(place.getX());
      nodePlace.setY(place.getY());
      nodePlace.setName(place.getName());
      via.setPlace(nodePlace);
      viaSet.add(via);
    });
    neo4jService.saveOrUpdateVehicle(nodeVehicle);
    if(!CollectionUtils.isEmpty(needRemove)) {
      neo4jService.deleteVia(needRemove);
    }

    return SimpleResponse.createSuccessResponse();
  }

  @Transactional(rollbackFor = Exception.class, value = "multiTransactionManager")
  public int saveOrUpdateVehicle(Vehicle vehicle) {
    if(null == vehicle.getId()) {
      return vehicleDao.saveVehicle(vehicle);
    } else {
      return vehicleDao.updateVehicle(vehicle);
    }
  }
}
