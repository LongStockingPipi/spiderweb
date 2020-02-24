package pers.jason.spiderweb.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Jason
 * 交通工具
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

  private Long id;

  private Integer type;

  private String code;

  //始发站
  private Long departureStation;

  //终点站
  private Long destinationStation;

  //时刻表
  private List<TimeTable> timeTable;

  public enum VehicleType {
    TRAIN("train", "火车", 1),
    BUS("bus", "公交、客车", 2);

    public String name;

    public String cName;

    public Integer code;

    VehicleType(String name, String cName, Integer code) {
      this.name = name;
      this.code = code;
      this.cName = cName;
    }
  }

  public static VehicleType getTypeByCode(Integer code) {

    if(VehicleType.TRAIN.code.equals(code)) {
      return VehicleType.TRAIN;
    } else if(VehicleType.BUS.code.equals(code)) {
      return VehicleType.BUS;
    } else {
      throw new RuntimeException("无效的交通工具类型");
    }
  }
}
