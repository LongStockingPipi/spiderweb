package pers.jason.spiderweb.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jason
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLocation {

  private Long locationId;

  private Long userId;

  //是否乘坐交通工具
  private Boolean useVehicle;

  //交通工具
  private Long vehicle;

  private Long startTime;

  private Long endTime;

  //起点
  private Long startPlace;

  //终点
  private Long endPlace;

  //活动区域
  private Long place;

  //车厢
  private String carriage;

  //座位
  private String seat;

  //检票口/登机口
  private String wicket;

  //出站口
  private String exit;

}
