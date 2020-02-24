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
@RelationshipEntity
public class By {

  @Id
  @GeneratedValue
  private Long graphId;

  @StartNode
  private NodePerson person;

  @EndNode
  private NodeVehicle vehicle;

  @Property("start_time")
  private Long startTime;

  @Property("start_station_id")
  private Long startStationId;

  @Property("start_station")
  private String startStation;

  @Property("wicket")
  private String wicket;

  @Property("end_station_id")
  private Long endStationId;

  @Property("end_station")
  private String endStation;

  @Property("exit")
  private String exit;

  @Property("end_time")
  private Long endTime;

  //车厢
  @Property("carriage")
  private String carriage;

  //座位
  @Property("seat")
  private String seat;

  public By(Long startTime, Long startStationId, String wicket, Long endStationId, String exit, Long endTime, String carriage, String seat) {
    this.startTime = startTime;
    this.startStationId = startStationId;
    this.wicket = wicket;
    this.endStationId = endStationId;
    this.exit = exit;
    this.endTime = endTime;
    this.carriage = carriage;
    this.seat = seat;
  }
}
