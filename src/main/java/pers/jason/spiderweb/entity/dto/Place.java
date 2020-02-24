package pers.jason.spiderweb.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jason
 * 地点
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Place {

  //sql主键
  private Long placeId;

  private String name;

  //纬度
  private Double x;

  //经度
  private Double y;

  private Integer type;

  public enum PlaceType {
    SIMPLE("simple", 0),
    RAILWAY_STATION("railwayStation", 1),
    BUS_STATION("busStation", 2);

    public String name;

    public Integer code;

    PlaceType(String name, Integer code) {
      this.name = name;
      this.code = code;
    }
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 17 * result + placeId.hashCode();
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof Place)) {
      return false;
    }
    Place place = (Place) obj;
    if (this == place) {
      return true;
    }
    if (place.placeId.equals(this.placeId)) {
      return true;
    } else {
      return false;
    }
  }

}
