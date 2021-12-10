package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.ext.ddd.application.UseCases;
import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentInfo;
import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentProfile;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.ApplicationService;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.assembler.ResidentAssembler;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.query.QueryResidentInfos;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.query.QueryResidents;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.CancelVaccineRequest;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.ChooseVaccineRequest;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.MakeAppointmentRequest;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.ReplaceResidentRequest;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.rest.ResidentApi;

@RestController
@RequiredArgsConstructor
class ResidentController implements ResidentApi {

  final ApplicationService service;
  final ResidentAssembler assembler;
  final UseCases useCases;
  final QueryResidents query;
  final QueryResidentInfos queryInfo;

  @Override
  public long makeAppointment(MakeAppointmentRequest request) {
    var command = assembler.toMakeAppointmentCommand(request);
    return service.makeAppointment(command);
  }

  @Override
  public void chooseVaccine(long id, ChooseVaccineRequest request) {
    var command = assembler.toChooseVaccineCommand(request);
    command.setId(id);
    useCases.execute(command);
    // service.chooseVaccine(command);
  }

  @Override
  public void cancelVaccine(long id, CancelVaccineRequest request) {
    var command = assembler.toCancelVaccineCommand(request);
    command.setId(id);
    useCases.execute(command);
    // service.cancelVaccine(command);
  }

  @Override
  public List<ResidentProfile> getResidents() {
    return query.getResidents();
  }

  @Override
  public List<ResidentInfo> getResidentInfo() {
    return queryInfo.getResidentInfo();
  }

  @Override
  public void replaceResidentProfile(long id, ReplaceResidentRequest request) {
    var command = assembler.toReplaceResidentProfileCommand(request);
    command.setId(id);
    useCases.execute(command);
  }
}
