package pers.jason.spiderweb.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jason
 * 人
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

  private String name;

  //sql主键
  private Long id;

  private String tel;

  private Integer gender;

  private String email;

  private Integer age;

  private Integer infected;

  private Long diseaseTime;

  private Long definiteTime;


  public enum Gender {
    MALE("male", 0),
    FEMALE("female", 1);

    public String name;

    public Integer code;

    Gender(String name, Integer code) {
      this.name = name;
      this.code = code;
    }
  }

  public enum Status {
    HEALTHY("healthy", 0),
    INFECTED("infected", 1);
    public String name;

    public Integer code;

    Status(String name, Integer code) {
      this.name = name;
      this.code = code;
    }
  }
}
