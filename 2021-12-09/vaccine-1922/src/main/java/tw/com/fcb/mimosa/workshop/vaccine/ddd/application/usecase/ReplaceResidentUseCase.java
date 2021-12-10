package tw.com.fcb.mimosa.workshop.vaccine.ddd.application.usecase;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.ext.ddd.application.ApplicationUseCase;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.ResidentProfileReplaced;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.AppointmentRepository;

@Service
@RequiredArgsConstructor
public class ReplaceResidentUseCase implements ApplicationUseCase<ResidentProfileReplaced, Void> {

  final AppointmentRepository repository;
  final ApplicationEventPublisher publisher;

  @Override
  public Void execute(@NotNull @Valid ResidentProfileReplaced command) {
    // TODO Auto-generated method stub
    var domain = repository.findById(command.getId());

    if (command.getNhiNo().equals(domain.getNhiNo()) && StringUtils.hasText(command.getPhoneNo())) {
      domain.setPhoneNo(command.getPhoneNo());
      repository.save(domain);
      var event = new ResidentProfileReplaced();
      event.setPhoneNo(domain.getPhoneNo());
      event.setId(domain.getId());
      publisher.publishEvent(event);
    }
    return null;
  }

}
