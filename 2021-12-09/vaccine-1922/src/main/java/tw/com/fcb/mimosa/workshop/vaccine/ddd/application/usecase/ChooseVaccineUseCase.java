package tw.com.fcb.mimosa.workshop.vaccine.ddd.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import tw.com.fcb.mimosa.ext.ddd.application.ApplicationUseCase;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.assembler.CommandAssembler;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.ChooseVaccine;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.AppointmentRepository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.event.VaccineChose;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChooseVaccineUseCase implements ApplicationUseCase<ChooseVaccine, Void> {

  final AppointmentRepository repository;
  final CommandAssembler assembler;
  final ApplicationEventPublisher publisher;

  // Anemic Model Style
  @Override
  public Void execute(@NotNull @Valid ChooseVaccine command) {
    var domain = repository.findById(command.getId());
    var toAppend = command.getVaccines().stream()
        .map(assembler::toChoose)
        .collect(Collectors.toList());
    domain.getChooses().addAll(toAppend);
    repository.save(domain);
    //自已發佈
    publisher.publishEvent(new VaccineChose(command.getVaccines()));
    return null;
  }
}
