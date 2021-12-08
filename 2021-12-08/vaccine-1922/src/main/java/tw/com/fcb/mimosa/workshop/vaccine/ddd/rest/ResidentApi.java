package tw.com.fcb.mimosa.workshop.vaccine.ddd.rest;

import org.springframework.web.bind.annotation.*;

import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentProfile;

import java.util.List;

@RequestMapping("/residents")
public interface ResidentApi {

  @PostMapping
  long makeAppointment(
      @RequestBody MakeAppointmentRequest request);

  @PutMapping("/{id}/vaccines")
  void chooseVaccine(@PathVariable("id") long id,
      @RequestBody ChooseVaccineRequest request);

  @DeleteMapping("/{id}/vaccines")
  void cancelVaccine(@PathVariable("id") long id,
      @RequestBody CancelVaccineRequest request);

  @GetMapping
  List<ResidentProfile> getResidents();

  @PutMapping("/{id}")
  void replaceResidentProfile(@PathVariable("id") long id,
      @RequestBody ReplaceResidentRequest request);
}
