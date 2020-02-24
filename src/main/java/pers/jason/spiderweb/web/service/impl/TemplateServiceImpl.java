package pers.jason.spiderweb.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pers.jason.spiderweb.entity.dto.Vehicle;
import pers.jason.spiderweb.entity.vo.InfectedPerson;
import pers.jason.spiderweb.graph.neo4j.pojo.By;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePerson;
import pers.jason.spiderweb.smg.SmsTemplateService;
import pers.jason.spiderweb.util.DateUtil;
import pers.jason.spiderweb.web.service.TemplateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class TemplateServiceImpl implements TemplateService {

  @Autowired
  private Map<String, SmsTemplateService> smsTemplateServiceMap;

  private Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);



  @Override
  public void sendSms(String message, String tel) {
    logger.info("向" + tel + "发送短信通知：" + message);
  }

  public List<InfectedPerson> getHtmlTemplateContent(List<NodePerson> personList) {
    if(CollectionUtils.isEmpty(personList)) {
      throw new RuntimeException("无接触感染人员数据");
    }
    List<InfectedPerson> infectedPeople = new ArrayList<>();
    personList.forEach(person -> {
      Set<By> bySet = person.getLocations();
      bySet.forEach(by -> {
        Integer vehicleTypeCode = by.getVehicle().getType();
        String typeName = Vehicle.getTypeByCode(vehicleTypeCode).name;
        String vehicleCode = by.getVehicle().getCode();
        String seat = by.getCarriage() + " " + by.getSeat();
        String startTime = DateUtil.getDateByTime(by.getStartTime());
        String endTime = DateUtil.getDateByTime(by.getEndTime());
        String startPlace = by.getStartStation();
        String endPlace = by.getEndStation();
        InfectedPerson infectedPerson = new InfectedPerson(typeName, vehicleCode, seat, startTime, endTime, startPlace, endPlace);
        infectedPeople.add(infectedPerson);
      });
    });
    return infectedPeople;
  }

  @Override
  public String getSmsTemplateContent(List<NodePerson> personList) {
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
        String typeName = type.name;
        String serviceClassName = typeName + "SmsTemplateService";
        SmsTemplateService templateService = smsTemplateServiceMap.get(serviceClassName);
        if(null == templateService) {
          throw new RuntimeException("无效的交通工具类型");
        }
        sb = templateService.getContactHistory(by);
        stringBuilder.append(sb);
        stringBuilder.append(System.getProperty("line.separator"));
      });
    });
    return stringBuilder.toString();
  }
}
