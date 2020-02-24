package pers.jason.spiderweb.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtil {

  public static final String DATE_FORMAT_SIMPLE = "yyyy-MM-dd HH:mm:ss";

  public static Long getTime(String date, String format) {
    return  LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format)).toInstant(ZoneOffset.of("+8")).toEpochMilli();
  }

  public static String getDateByTime(Long time) {
    DateTimeFormatter df= DateTimeFormatter.ofPattern(DATE_FORMAT_SIMPLE);
    return df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.of("Asia/Shanghai")));
  }
}
