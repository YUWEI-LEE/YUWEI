package tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.event;

import lombok.Data;

//Event
@Data
public class AppointmentMade {

  long residentId;
  String nhiNo;
  String phoneNo;

}
