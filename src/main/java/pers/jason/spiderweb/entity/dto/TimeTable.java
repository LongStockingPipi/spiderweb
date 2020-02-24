package pers.jason.spiderweb.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable {

  private Long vehicleId;

  private Long placeId;

  private String arriveTime;

  private String setOutTime;

  private String vehicleCode;

  private String placeName;
}
