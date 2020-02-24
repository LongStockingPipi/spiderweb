package pers.jason.spiderweb.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.jason.spiderweb.entity.dto.Vehicle;

import javax.validation.constraints.NotNull;

/**
 * @author Jason
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLocationRequest {

  @NotNull(message = "用户无效")
  private Long userId;

  //是否乘坐交通工具
  @NotNull(message = "请选择是否乘坐交通工具")
  private Boolean useVehicle;

  //交通工具
  private Vehicle vehicle;

  @NotNull(message = "起始活动时间不能为空")
  private Long startTime;

  @NotNull(message = "结束活动时间不能为空")
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
