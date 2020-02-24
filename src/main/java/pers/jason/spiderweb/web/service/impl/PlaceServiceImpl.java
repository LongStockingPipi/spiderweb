package pers.jason.spiderweb.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.jason.spiderweb.entity.dto.Place;
import pers.jason.spiderweb.entity.dto.Vehicle;
import pers.jason.spiderweb.exception.InvalidEnumerationType;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePlace;
import pers.jason.spiderweb.graph.neo4j.service.Neo4jService;
import pers.jason.spiderweb.web.dao.PlaceDao;
import pers.jason.spiderweb.web.service.PlaceService;

import java.util.List;

import static pers.jason.spiderweb.entity.dto.Place.PlaceType.BUS_STATION;
import static pers.jason.spiderweb.entity.dto.Place.PlaceType.RAILWAY_STATION;
import static pers.jason.spiderweb.entity.dto.Place.PlaceType.SIMPLE;

@Service
public class PlaceServiceImpl implements PlaceService {

  @Autowired
  private Neo4jService neo4jService;

  @Autowired
  private PlaceDao placeDao;

  @Override
  public NodePlace findPlaceById(Long id) {
    NodePlace nodePlace = neo4jService.findPlaceBySqlId(id);
    if(null == nodePlace || null == nodePlace.getGraphId()) {
      Place place = placeDao.findPlaceById(id);
      if(null == place) {
        return null;
      }
      nodePlace = new NodePlace();
      nodePlace.setName(place.getName());
      nodePlace.setId(id);
      nodePlace.setX(place.getX());
      nodePlace.setY(place.getY());
      Integer placeType = place.getType();
      Place.PlaceType type = getTypeByCode(placeType);
      if(null == type) {
        throw new RuntimeException("无效的交通工具");
      }
      if(RAILWAY_STATION.equals(type)) {
        nodePlace.addLabels("RAILWAY_STATION");
      }
      if(BUS_STATION.equals(type)) {
        nodePlace.addLabels("BUS_STATION");
      }
    }
    return nodePlace;
  }

  @Override
  public List<Place> findPlacesByName(List<String> name, Integer type) {
    return placeDao.findPlacesByName(name, type);
  }

  @Override
  public Place.PlaceType getPlaceTypeByVehicle(Integer vehicleType) {
    if(Vehicle.VehicleType.TRAIN.code.equals(vehicleType)) {
      return RAILWAY_STATION;
    } else if(Vehicle.VehicleType.BUS.code.equals(vehicleType)) {
      return BUS_STATION;
    } else {
      throw new InvalidEnumerationType(String.valueOf(vehicleType));
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class, value = "multiTransactionManager")
  public void savePlaces(List<Place> places) {
    placeDao.savePlaces(places);
  }

  private Place.PlaceType getTypeByCode(Integer type) {
    if(SIMPLE.code.equals(type)) {
      return SIMPLE;
    }
    if(RAILWAY_STATION.code.equals(type)) {
      return RAILWAY_STATION;
    }
    if(BUS_STATION.code.equals(type)) {
      return BUS_STATION;
    }
    return null;
  }
}
