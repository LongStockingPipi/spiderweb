package pers.jason.spiderweb.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.jason.spiderweb.entity.dto.ActivityLocation;
import pers.jason.spiderweb.entity.dto.Place;

import java.util.List;

@Mapper
public interface PlaceDao {

  Place findPlaceById(@Param("id") Long id);

  String getNameById(@Param("id") Long id);

  List<Place> findPlacesByName(@Param("nameList") List<String> name, @Param("type") Integer type);

  void savePlaces(List<Place> places);
}
