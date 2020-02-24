package pers.jason.spiderweb.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jason.spiderweb.entity.dto.TimeTable;
import pers.jason.spiderweb.web.dao.TimeTableDao;
import pers.jason.spiderweb.web.service.TimeTableService;

import java.util.List;

@Service
public class TimeTableServiceImpl implements TimeTableService {

  @Autowired
  private TimeTableDao timeTableDao;

  @Override
  public void save(List<TimeTable> timeTableList) {
    timeTableDao.save(timeTableList);
  }
}
