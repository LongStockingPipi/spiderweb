package pers.jason.spiderweb.web.service;

import pers.jason.spiderweb.entity.vo.InfectedPerson;
import pers.jason.spiderweb.graph.neo4j.pojo.NodePerson;

import java.util.List;

public interface TemplateService {

  String TEMPLATE_FOR_MOBILE = "request_mobile";

  String TEMPLATE_FOR_HTML = "request_html";

  String TEMPLATE_FOR_SMS = "request_sms";

  String TEMPLATE_FOR_JSON = "request_json";

  void sendSms(String message, String tel);

  String getSmsTemplateContent(List<NodePerson> personList);

  List<InfectedPerson> getHtmlTemplateContent(List<NodePerson> personList);
}
