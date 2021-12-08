package tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command;

import java.util.List;
import lombok.Data;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.Vaccine;

@Data
public class CancelVaccine {

  long id;
  List<Vaccine> vaccines;

}
