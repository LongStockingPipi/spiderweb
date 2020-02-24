package pers.jason.spiderweb.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfectedPerson {

  private String vehicleType;

  private String vehicleCode;

  private String seat;

  private String startTime;

  private String endTime;

  private String startPlace;

  private String endPlace;
}
