package pers.jason.spiderweb.web.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.jason.spiderweb.entity.dto.TimeTable;

import java.util.List;

@Mapper
public interface TimeTableDao {

  void save(List<TimeTable> timeTableList);
}
