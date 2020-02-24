package pers.jason.spiderweb.graph.neo4j.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePerson;

import java.util.List;

@Repository
public interface PersonRepository extends Neo4jRepository<NodePerson, Long> {

  @Query("match(n:PERSON) where n.sql_id = {0} return n")
  NodePerson findBySqlId(Long id);

  @Query("match (l:INFECTED)-[l1]->(m)<-[l2]-(n:PERSON {sql_id:{0}}) where l2.start_time <= l1.end_time and l1.start_time <= l2.end_time return l, l1, m")
  List<NodePerson> findOneStepPatientWithPersonId(Long id);
}
