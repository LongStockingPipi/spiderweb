package pers.jason.spiderweb.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.jason.spiderweb.entity.dto.ActivityLocation;
import pers.jason.spiderweb.entity.dto.Vehicle;
import pers.jason.spiderweb.entity.vo.InfectedPerson;
import pers.jason.spiderweb.graph.neo4j.pojo.By;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePerson;
import pers.jason.spiderweb.graph.neo4j.pojo.NodeVehicle;
import pers.jason.spiderweb.graph.neo4j.service.Neo4jService;
import pers.jason.spiderweb.web.dao.LocationDao;
import pers.jason.spiderweb.web.dao.PlaceDao;
import pers.jason.spiderweb.web.request.ActivityLocationRequest;
import pers.jason.spiderweb.web.service.CoreService;
import pers.jason.spiderweb.web.service.PersonService;
import pers.jason.spiderweb.web.service.TemplateService;
import pers.jason.spiderweb.web.service.VehicleService;

import java.util.List;

/**
 * @author Jason
 */
@Service
public class CoreServiceImpl implements CoreService {

  @Autowired
  private Neo4jService neo4jService;

  @Autowired
  private VehicleService vehicleService;

  @Autowired
  private PersonService personService;

  @Autowired
  private LocationDao locationDao;

  @Autowired
  private PlaceDao placeDao;

  @Autowired
  private TemplateService templateService;

  @Override
  @Transactional(readOnly = true, value = "multiTransactionManager")
  public List<InfectedPerson> checkForHtml(Long personId) {
    List<NodePerson> personList = neo4jService.findOneStepPatientWithPersonId(personId);
    List<InfectedPerson> content = templateService.getHtmlTemplateContent(personList);
    return content;
  }

  @Override
  @Transactional(rollbackFor = Exception.class, value = "multiTransactionManager")
  public void registerSchedule(final ActivityLocationRequest request) {
    Long personId = request.getUserId();
    Vehicle vehicle = request.getVehicle();
    Long startPlaceId = request.getStartPlace();
    Long endPlaceId = request.getEndPlace();
    Long startTime = request.getStartTime();
    Long endTime = request.getEndTime();

    //1. 校验
    //数据准确性、时间是否重叠
    if(personService.validityOfTestTime(personId, startTime, endTime)) {
      throw new RuntimeException("该时间段已经存在行程，请确认");
    }
    //2. 存储mysql
    ActivityLocation location = getActivityLocation(request);
    locationDao.saveLocation(location);

    //存储neo4j
    //2.1. 获取person节点
    NodePerson nodePerson = personService.findPersonById(personId);
    //2.2. 获取vehicle节点
    Long vehicleId = vehicle.getId();
    NodeVehicle nodeVehicle = vehicleService.findVehicleById(vehicleId);
    //2.3. 创建person-vehicle关系
    By by = new By(startTime, startPlaceId, request.getWicket(), endPlaceId, request.getExit()
        , endTime, request.getCarriage(), request.getSeat());
    by.setStartStation(placeDao.getNameById(startPlaceId));
    by.setEndStation(placeDao.getNameById(endPlaceId));
    //2.4. 组装、存储
    by.setPerson(nodePerson);
    by.setVehicle(nodeVehicle);
    nodePerson.getLocations().add(by);
    neo4jService.saveOrUpdatePerson(nodePerson);

    //3. 检测感染人群
  }

  private ActivityLocation getActivityLocation(final ActivityLocationRequest request) {
    ActivityLocation location = new ActivityLocation();
    if(null == request) {
      return location;
    }
    if(null == request.getUserId() || null == request.getUseVehicle()) {
      return location;
    }
    location.setUserId(request.getUserId());
    location.setCarriage(request.getCarriage());
    location.setEndPlace(request.getEndPlace());
    location.setEndTime(request.getEndTime());
    location.setPlace(request.getPlace());
    location.setSeat(request.getSeat());
    location.setStartPlace(request.getStartPlace());
    location.setStartTime(request.getStartTime());
    location.setUseVehicle(request.getUseVehicle());
    location.setWicket(request.getWicket());
    location.setExit(request.getExit());
    Vehicle vehicle = request.getVehicle();
    if(null != vehicle && null != vehicle.getId()) {
      location.setVehicle(vehicle.getId());
    }
    return location;
  }
}
