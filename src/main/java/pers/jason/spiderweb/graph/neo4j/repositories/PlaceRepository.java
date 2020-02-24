package pers.jason.spiderweb.graph.neo4j.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePlace;

import java.util.List;

@Repository
public interface PlaceRepository extends Neo4jRepository<NodePlace, Long> {

  @Query("match(n:SITE) where n.sql_id = {0} return n")
  NodePlace findBySqlId(Long id);

  @Query("match(n:SITE) where n.sql_id in {0} return n")
  List<NodePlace> findPlacesById(List<Long> idList);
}
