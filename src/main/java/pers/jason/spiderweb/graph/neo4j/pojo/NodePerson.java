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
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jason
 * 人
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity(label = "PERSON")
public class NodePerson {

  //neo4j id
  @Id
  @GeneratedValue
  private Long graphId;

  //mysql id
  @Property("sql_id")
  private Long id;

  @Property("name")
  private String name;

  @Property("tel")
  private String tel;

  @Property("gender")
  private Integer gender;

  @Property("email")
  private String email;

  @Property("age")
  private Integer age;

  @Property("disease_time")
  private Long diseaseTime;

  @Property("definite_time")
  private Long definiteTime;

  @Relationship(type = "BY")
  private Set<By> locations = new HashSet<>();

  @Labels
  private Set<String> labels = new HashSet<>();

  public void addLabels(String ... newLabels) {
    for(String l : newLabels) {
      labels.add(l);
    }
  }

}
