package pers.jason.spiderweb.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.jason.spiderweb.entity.dto.Person;

@Mapper
public interface PersonDao {

  void savePerson(Person person);

  Person findPersonById(@Param("id") Long id);

  Integer validityOfTestTime(@Param("id") Long id, @Param("startTime") Long startTime, @Param("endTime") Long endTime);

}
