package pers.jason.spiderweb.web.service;

import pers.jason.spiderweb.entity.dto.Person;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePerson;

public interface PersonService {

  /**
   * 先从neo4j获取数据，若没有，则从mysql获取，在封装成neo4j数据结构返回
   * @param id
   * @return
   */
  NodePerson findPersonById(Long id);

  /**
   * 检验用户行程时间是否合法
   * 时间是否已经被已注册的行程占用
   * @param userId
   * @param startTime
   * @param endTime
   * @return
   */
  Boolean validityOfTestTime(Long userId, Long startTime, Long endTime);
}
