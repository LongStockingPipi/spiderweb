package pers.jason.spiderweb.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.jason.spiderweb.entity.dto.Person;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePerson;
import pers.jason.spiderweb.graph.neo4j.service.Neo4jService;
import pers.jason.spiderweb.web.dao.PersonDao;
import pers.jason.spiderweb.web.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

  private Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

  @Autowired
  private Neo4jService neo4jService;

  @Autowired
  private PersonDao personDao;

  @Override
  public NodePerson findPersonById(Long id) {
    NodePerson nodePerson = neo4jService.findPersonBySqlId(id);
    if(null == nodePerson) {
      Person person = personDao.findPersonById(id);
      nodePerson = new NodePerson();
      nodePerson.setId(id);
      nodePerson.setName(person.getName());
      nodePerson.setGender(person.getGender());
      nodePerson.setTel(person.getTel());
      nodePerson.setEmail(person.getEmail());
      nodePerson.setAge(person.getAge());
      nodePerson.setDiseaseTime(person.getDiseaseTime());
      nodePerson.setDefiniteTime(person.getDefiniteTime());
      if(Person.Status.INFECTED.code.equals(person.getInfected())) {
        nodePerson.addLabels("INFECTED");
      }
    }
    return nodePerson;
  }

  @Override
  public Boolean validityOfTestTime(Long userId, Long startTime, Long endTime) {
    if(personDao.validityOfTestTime(userId, startTime, endTime) > 0) {
      return false;
    }
    return true;
  }
}
