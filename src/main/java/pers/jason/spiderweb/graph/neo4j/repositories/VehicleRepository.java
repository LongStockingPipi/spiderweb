package pers.jason.spiderweb.graph.neo4j.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pers.jason.spiderweb.graph.neo4j.pojo.NodeVehicle;

import java.util.List;

@Repository
public interface VehicleRepository extends Neo4jRepository<NodeVehicle, Long> {

  @Query("match(n:VEHICLE) where n.sql_id = {0} return n")
  NodeVehicle findBySqlId(Long id);

  @Query("match p = (n:VEHICLE) - [l:VIA] -> (m:SITE) where n.sql_id = {0} return p")
  NodeVehicle findVehicleAndPlacesById(Long id);

  @Query("match(n) - [l:VIA] - (m) where l.id in {0} delete l")
  void deleteViaByGraphId(List<Long> idList);
}
