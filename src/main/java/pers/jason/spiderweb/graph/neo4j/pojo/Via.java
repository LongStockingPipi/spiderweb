package pers.jason.spiderweb.graph.neo4j.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RelationshipEntity(type="WIA")
public class Via {

  @StartNode
  private NodeVehicle vehicle;

  @EndNode
  private NodePlace place;

  @Id
  @GeneratedValue
  private Long graphId;

  @Property("num")
  private Integer num;

  @Property("arrival_time")
  private String arrivalTime;

  @Property("set_out_time")
  private String setOutTime;

}
