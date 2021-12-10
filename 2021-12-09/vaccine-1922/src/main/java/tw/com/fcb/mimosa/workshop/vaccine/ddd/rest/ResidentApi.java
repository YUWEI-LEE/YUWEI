package tw.com.fcb.mimosa.workshop.vaccine.ddd.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentInfo;
import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentProfile;

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

  @GetMapping("/info")
  List<ResidentInfo> getResidentInfo();

  @PutMapping("/{id}")
  void replaceResidentProfile(@PathVariable("id") long id,
      @RequestBody ReplaceResidentRequest request);
}
