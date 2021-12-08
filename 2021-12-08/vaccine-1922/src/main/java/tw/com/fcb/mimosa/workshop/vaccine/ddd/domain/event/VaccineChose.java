package tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.Vaccine;

import java.util.List;

@Data
@AllArgsConstructor
public class VaccineChose {

  List<Vaccine> vaccines;

}
