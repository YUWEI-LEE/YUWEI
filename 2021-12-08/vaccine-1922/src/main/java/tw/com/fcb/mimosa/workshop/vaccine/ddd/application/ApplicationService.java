package tw.com.fcb.mimosa.workshop.vaccine.ddd.application;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.assembler.CommandAssembler;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.CancelVaccine;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.ChooseVaccine;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.MakeAppointment;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.AppointmentRepository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.Choose;

@Service
@RequiredArgsConstructor
public class ApplicationService {

  final AppointmentRepository repository;
  final CommandAssembler assembler;

  public long makeAppointment(MakeAppointment command) {
    return repository.save(assembler.toDomain(command));
  }

  // 這是 anemic model style, 這段可以收到 domain 物件內改成 rich model style
  public void chooseVaccine(ChooseVaccine command) {
    var domain = repository.findById(command.getId());
    var toAppend = command.getVaccines().stream()
        .map(assembler::toChoose)
        .collect(Collectors.toList());
    domain.getChooses().addAll(toAppend);
    repository.save(domain);
  }

  // 這是 anemic model style, 這段可以收到 domain 物件內改成 rich model style
  public void cancelVaccine(CancelVaccine command) {
    var domain = repository.findById(command.getId());
    //搬到domain Appointment
    //    var toDrop = domain.getChooses().stream()
    //        .filter(dbChoose -> command.getVaccines()
    //            .contains(dbChoose.getVaccine()))
    //        .collect(Collectors.toList());
    //    domain.getChooses().removeAll(toDrop);
    //
    //    var cancels = toDrop.stream()
    //        .map(Choose::getVaccine)
    //        .map(assembler::toCancel)
    //        .collect(Collectors.toList());
    //    domain.getCancels().addAll(cancels);
    //
    domain.cancelVaccine(command.getVaccines());
    repository.save(domain);
  }
}
