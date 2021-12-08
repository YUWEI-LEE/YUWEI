package tw.com.fcb.mimosa.workshop.vaccine.ddd.application.assembler;

import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.MakeAppointment;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.Appointment;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.Cancel;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.Choose;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.Vaccine;

@Mapper
public interface CommandAssembler {

  Appointment toDomain(MakeAppointment command);

  default Choose toChoose(Vaccine vaccine) {
    var domain = new Choose();
    domain.setChooseTime(LocalDateTime.now());
    domain.setVaccine(vaccine);
    return domain;
  }

  default Cancel toCancel(Vaccine vaccine) {
    var domain = new Cancel();
    domain.setCancelTime(LocalDateTime.now());
    domain.setVaccine(vaccine);
    return domain;
  }
}
