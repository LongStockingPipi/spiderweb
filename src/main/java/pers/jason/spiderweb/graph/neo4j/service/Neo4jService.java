package pers.jason.spiderweb.graph.neo4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePerson;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePlace;
import pers.jason.spiderweb.graph.neo4j.pojo.NodeVehicle;
import pers.jason.spiderweb.graph.neo4j.repositories.PersonRepository;
import pers.jason.spiderweb.graph.neo4j.repositories.PlaceRepository;
import pers.jason.spiderweb.graph.neo4j.repositories.VehicleRepository;

import java.util.List;

@Service
public class Neo4jService {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private PlaceRepository placeRepository;

  public NodePerson saveOrUpdatePerson(NodePerson nodePerson) {
    return personRepository.save(nodePerson);
  }

  public NodeVehicle saveOrUpdateVehicle(NodeVehicle nodeVehicle) {
    return vehicleRepository.save(nodeVehicle);
  }

  public void deleteVia(List<Long> grapgIdList) {}

  public NodePerson findPersonBySqlId(Long sqlId) {
    return personRepository.findBySqlId(sqlId);
  }

  public NodeVehicle findVehicleBySqlId(Long id) {
    return vehicleRepository.findBySqlId(id);
  }

  public NodeVehicle findVehicleAndPlacesById(Long id) {
    return vehicleRepository.findVehicleAndPlacesById(id);
  }

  public NodePlace findPlaceBySqlId(Long id) {
    return placeRepository.findBySqlId(id);
  }

  public List<NodePerson> findOneStepPatientWithPersonId(Long userId) {
    return personRepository.findOneStepPatientWithPersonId(userId);
  }

  public List<NodePlace> findPlacesById(List<Long> idList) {
    return placeRepository.findPlacesById(idList);
  }
}
