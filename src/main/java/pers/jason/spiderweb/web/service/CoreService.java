package pers.jason.spiderweb.web.service;

import pers.jason.spiderweb.entity.vo.InfectedPerson;
import pers.jason.spiderweb.web.request.ActivityLocationRequest;

import java.util.List;

public interface CoreService {

  /**
   * 主动检测是否接触过病患
   * @param personId
   * @return
   */
  List<InfectedPerson> checkForHtml(Long personId);

  /**
   * 用户注册行程
   * @param request
   */
  void registerSchedule(ActivityLocationRequest request);
}
