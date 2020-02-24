package pers.jason.spiderweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jason.spiderweb.entity.dto.Vehicle;
import pers.jason.spiderweb.entity.vo.InfectedPerson;
import pers.jason.spiderweb.util.DateUtil;
import pers.jason.spiderweb.web.request.ActivityLocationRequest;
import pers.jason.spiderweb.web.response.SimpleResponse;
import pers.jason.spiderweb.web.service.CoreService;

import java.util.List;

/**
 * @author Jason
 */
@RestController
@RequestMapping("location")
public class ScheduleController {

  @Autowired
  private CoreService coreService;

  @GetMapping("check/{personId}")
  public SimpleResponse check(@PathVariable("personId") Long personId) {
    List<InfectedPerson> personList = coreService.checkForHtml(personId);
    return SimpleResponse.createSuccessResponse(personList);
  }

  /**
   * 注册用户行程
   * @param locationRequest
   */
  @GetMapping("register")
  public SimpleResponse registerSchedule(ActivityLocationRequest locationRequest) {
    Vehicle vehicle = new Vehicle();
    vehicle.setCode("G1236");
    vehicle.setId(1L);
    vehicle.setType(Vehicle.VehicleType.TRAIN.code);
    vehicle.setDepartureStation(1L);
    vehicle.setDestinationStation(2L);

    locationRequest.setUserId(3L);
    locationRequest.setStartPlace(6L);
    locationRequest.setEndPlace(7L);
    locationRequest.setStartTime(DateUtil.getTime("2020-02-24 09:43:00", DateUtil.DATE_FORMAT_SIMPLE));
    locationRequest.setEndTime(DateUtil.getTime("2020-02-24 14:34:00", DateUtil.DATE_FORMAT_SIMPLE));
    locationRequest.setUseVehicle(true);
    locationRequest.setWicket("6A");
    locationRequest.setSeat("3A");
    locationRequest.setCarriage("07");
    locationRequest.setExit("B");
    locationRequest.setVehicle(vehicle);
    coreService.registerSchedule(locationRequest);
    return SimpleResponse.createSuccessResponse();
  }
}
