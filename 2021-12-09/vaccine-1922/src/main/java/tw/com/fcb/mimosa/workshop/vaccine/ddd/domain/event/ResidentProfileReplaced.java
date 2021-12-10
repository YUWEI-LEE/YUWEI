package tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.event;

import lombok.Data;

@Data
public class ResidentProfileReplaced {

  Long residentId;
  String phone;

}
