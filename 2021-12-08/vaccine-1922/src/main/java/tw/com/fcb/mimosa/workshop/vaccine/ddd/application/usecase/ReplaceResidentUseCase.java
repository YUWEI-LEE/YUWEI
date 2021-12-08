package tw.com.fcb.mimosa.workshop.vaccine.ddd.application.usecase;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.ext.ddd.application.ApplicationUseCase;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.ReplaceResident;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.AppointmentRepository;

@Service
@RequiredArgsConstructor
public class ReplaceResidentUseCase implements ApplicationUseCase<ReplaceResident, Void> {

  final AppointmentRepository repository;

  @Override
  public Void execute(@NotNull @Valid ReplaceResident command) {
    // TODO Auto-generated method stub
    var domain = repository.findById(command.getId());
    
    if (command.getNhiNo().equals(domain.getNhiNo()) && StringUtils.hasText(command.getPhoneNo())) {
      domain.setPhoneNo(command.getPhoneNo());
      repository.save(domain);
    }
    return null;
  }

}
