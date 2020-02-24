package pers.jason.spiderweb.smg;

import org.springframework.stereotype.Service;
import pers.jason.spiderweb.graph.neo4j.pojo.By;
import pers.jason.spiderweb.graph.neo4j.pojo.NodeVehicle;
import pers.jason.spiderweb.util.DateUtil;

import static pers.jason.spiderweb.entity.dto.Vehicle.VehicleType.TRAIN;

@Service
public class TrainSmsTemplateService extends SmsTemplateService {


  @Override
  public StringBuilder getContactHistory(By by) {
    StringBuilder sb = new StringBuilder();
    NodeVehicle vehicle = by.getVehicle();
    sb.append("交通工具：").append(TRAIN.cName).append("; ");
    sb.append("编号：").append(vehicle.getCode()).append("; ");
    sb.append("座位：").append(by.getCarriage()).append(" ").append(by.getSeat()).append("; ");
    String startTime = DateUtil.getDateByTime(by.getStartTime());
    String endTime = DateUtil.getDateByTime(by.getEndTime());
    sb.append("乘车时间：").append(startTime).append("-").append(endTime).append("; ");
    sb.append("途径站：").append(by.getStartStation()).append("-").append(by.getEndStation()).append("; ");
    return sb;
  }
}
