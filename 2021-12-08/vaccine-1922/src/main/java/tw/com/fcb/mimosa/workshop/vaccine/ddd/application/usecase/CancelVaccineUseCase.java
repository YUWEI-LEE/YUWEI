package tw.com.fcb.mimosa.workshop.vaccine.ddd.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import tw.com.fcb.mimosa.ext.ddd.application.ApplicationUseCase;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.assembler.CommandAssembler;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.CancelVaccine;

import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.AppointmentRepository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.Choose;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.event.VaccineCanceled;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CancelVaccineUseCase implements ApplicationUseCase<CancelVaccine, Void> {

  final AppointmentRepository repository;

  // Rich model style
  @Override
  public Void execute(@NotNull @Valid CancelVaccine command) {
    var domain = repository.findById(command.getId());
    domain.cancelVaccine(command.getVaccines());
    repository.save(domain);
    return null;
  }
}
