package pers.jason.spiderweb.smg;

import org.springframework.util.CollectionUtils;
import pers.jason.spiderweb.entity.dto.Vehicle;
import pers.jason.spiderweb.graph.neo4j.pojo.By;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePerson;

import java.util.List;
import java.util.Set;

import static pers.jason.spiderweb.entity.dto.Vehicle.VehicleType.TRAIN;

public abstract class SmsTemplateService {

  public abstract StringBuilder getContactHistory(By by);

  public String getContactHistory(List<NodePerson> personList) {
    if(CollectionUtils.isEmpty(personList)) {
      throw new RuntimeException("无接触感染人员数据");
    }
    StringBuilder stringBuilder = new StringBuilder();
    personList.forEach(person -> {
      Set<By> bySet = person.getLocations();
      bySet.forEach(by -> {
        StringBuilder sb;
        Integer vehicleTypeCode = by.getVehicle().getType();
        Vehicle.VehicleType type = Vehicle.getTypeByCode(vehicleTypeCode);
        if(TRAIN.equals(type)) {
          sb = getContactHistory(by);
          stringBuilder.append(sb);
          stringBuilder.append("\r\n");
        }
      });
    });
    return stringBuilder.toString();
  }



}
