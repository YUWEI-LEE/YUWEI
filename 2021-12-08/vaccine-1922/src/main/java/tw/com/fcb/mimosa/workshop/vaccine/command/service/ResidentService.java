package tw.com.fcb.mimosa.workshop.vaccine.command.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tw.com.fcb.mimosa.workshop.vaccine.command.ResidentMapper;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ChooseEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ResidentRepository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.CancelVaccine;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.ChooseVaccine;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.MakeAppointment;
import tw.com.fcb.mimosa.workshop.vaccine.command.web.ReplaceResidentProfile;
import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentProfile;

@Service
@Transactional
@RequiredArgsConstructor
public class ResidentService {

  final ResidentRepository repository;
  final ResidentMapper mapper;

  public void replaceResidentProfile(long id, ReplaceResidentProfile command) {
    var db = repository.findById(id).orElseThrow();
    if (StringUtils.hasText(command.getPhoneNo())) {
      db.setPhoneNo(command.getPhoneNo());
      repository.save(db);
    }
  }

  public void chooseVaccine(long id, ChooseVaccine command) {
    var db = repository.findById(id).orElseThrow();
    var append = command.getVaccines().stream()
        .map(mapper::toChooseEntity)
        //        .forEach(db.getChooses()::add);
        .collect(Collectors.toList());
    db.getChooses().addAll(append);
    repository.save(db);
  }

  public void cancelVaccine(long id, CancelVaccine command) {
    var db = repository.findById(id).orElseThrow();
    var drop = db.getChooses().stream()
        .filter(dbChoose -> command.getVaccines()
            .contains(dbChoose.getVaccine()))
        .collect(Collectors.toList());
    db.getChooses().removeAll(drop);
    var cancels = drop.stream()
        .map(ChooseEntity::getVaccine)
        .map(mapper::toCancelEntity)
        .collect(Collectors.toList());
    db.getCancels().addAll(cancels);
    repository.save(db);
  }

  public long makeAppointment(MakeAppointment command) {
    return repository.save(mapper.toEntity(command)).getId();
  }

  public List<ResidentProfile> getResidents() {
    return repository.findAll().stream().map(mapper::fromEntity).collect(Collectors.toList());
  }
}
