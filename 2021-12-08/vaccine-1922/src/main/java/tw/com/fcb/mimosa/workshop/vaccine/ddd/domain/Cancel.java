package tw.com.fcb.mimosa.workshop.vaccine.ddd.domain;

import java.time.LocalDateTime;
import lombok.Data;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.Vaccine;

@Data
public class Cancel {
  Long id;
  LocalDateTime cancelTime;
  Vaccine vaccine;
}
