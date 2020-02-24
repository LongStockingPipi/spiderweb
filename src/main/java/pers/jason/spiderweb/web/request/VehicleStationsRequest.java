package pers.jason.spiderweb.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.jason.spiderweb.entity.dto.TrainStation;
import pers.jason.spiderweb.entity.dto.Vehicle;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleStationsRequest implements Serializable {

  private Vehicle vehicle;

  private List<TrainStation> stationList;
}
