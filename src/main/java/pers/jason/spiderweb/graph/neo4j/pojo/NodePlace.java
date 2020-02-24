package pers.jason.spiderweb.graph.neo4j.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Labels;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import pers.jason.spiderweb.entity.dto.Place;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity(label = "SITE")
public class NodePlace {

  @Id
  @GeneratedValue
  private Long graphId;

  @Property("sql_id")
  private Long id;

  @Property("name")
  private String name;

  @Property("latitude")
  private Double x;

  @Property("longitude")
  private Double y;

  @Labels
  private Set<String> labels = new HashSet<>();

  public void addLabels(String ... newLabels) {
    for(String l : newLabels) {
      labels.add(l);
    }
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 17 * result + id.hashCode();
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof NodePlace)) {
      return false;
    }
    NodePlace place = (NodePlace) obj;
    if (this == place) {
      return true;
    }
    if (place.id.equals(this.id) && place.name.equals(this.name)) {
      return true;
    } else {
      return false;
    }
  }
}
