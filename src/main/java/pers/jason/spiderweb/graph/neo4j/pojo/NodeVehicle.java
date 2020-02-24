package pers.jason.spiderweb.graph.neo4j.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity(label = "VEHICLE")
public class NodeVehicle {

  @Id
  @GeneratedValue
  private Long graphId;

  @Property("sql_id")
  private Long id;

  @Property("type")
  private Integer type;

  @Property("code")
  private String code;

  //始发站
  @Property("departure_station")
  private String departureStation;

  //终点站
  @Property("destination_station")
  private String destinationStation;

  @Relationship(type = "VIA", direction=Relationship.INCOMING)
  private Set<Via> stations = new HashSet<>();
}
