package pers.jason.spiderweb.web.service;

import pers.jason.spiderweb.entity.dto.Place;
import pers.jason.spiderweb.entity.dto.Place.PlaceType;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePlace;

import java.util.List;

public interface PlaceService {

  /**
   * 先从neo4j获取数据，若没有，则从mysql获取，在封装成neo4j数据结构返回
   * @param id
   * @return
   */
  NodePlace findPlaceById(Long id);

  List<Place> findPlacesByName(List<String> name, Integer type);

  PlaceType getPlaceTypeByVehicle(Integer vehicleType);

  void savePlaces(List<Place> places);
}
