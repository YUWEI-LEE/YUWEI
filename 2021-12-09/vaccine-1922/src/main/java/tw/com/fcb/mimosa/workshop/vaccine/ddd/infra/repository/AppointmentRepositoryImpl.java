package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentInfo;
import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentProfile;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.Appointment;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.AppointmentRepository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.assembler.ResidentAssembler;

@Repository
@RequiredArgsConstructor
public class AppointmentRepositoryImpl implements AppointmentRepository {

  final ResidentRepository jpaRepository;
  final ResidentAssembler assembler;
  final ApplicationEventPublisher publisher;
  final ResidentProfileRepository jpaResidentProfileRepository;
  final ResidentInfoRepository jpaResidentInfoRepository;

  // save有成功就發出去
  @Override
  public long save(Appointment domain) {
    //    return jpaRepository
    //        .save(assembler.toEntity(domain))
    //        .getId();
    var saved = jpaRepository.save(assembler.toEntity(domain));
    domain.domainEvents().forEach(publisher::publishEvent);
    return saved.getId();
  }

  @Override
  public Appointment findById(long id) {
    return jpaRepository.findById(id)
        .map(assembler::fromEntity)
        .orElseThrow();
  }

  @Override
  public List<ResidentProfile> findResidents() {
    return jpaResidentProfileRepository.findAll().stream()
        .map(assembler::toProfile).collect(Collectors.toList());
  }

  @Override
  public List<ResidentInfo> findResidentsInfo() {
    return jpaResidentInfoRepository.findAll().stream()
        .map(assembler::toInfo).collect(Collectors.toList());
  }
}
