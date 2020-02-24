package pers.jason.spiderweb.web.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.jason.spiderweb.entity.dto.ActivityLocation;

@Mapper
public interface LocationDao {

  void saveLocation(ActivityLocation location);
}
