package pers.jason.spiderweb.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainStation extends Place implements Comparable<TrainStation>{

  private String startTime;

  private String endTime;

  @Override
  public int compareTo(TrainStation o) {
    if(null == this.startTime) {
      return -1;
    }
    if(null == this.endTime) {
      return 1;
    } else {
      return this.startTime.compareTo(o.startTime);
    }

  }

  public TrainStation(String name, Integer typeCode, String startTime, String endTime, Double x, Double y) {
    this.setName(name);
    this.setType(typeCode);
    this.setX(x);
    this.setY(y);
    this.startTime = startTime;
    this.endTime = endTime;
  }
}
