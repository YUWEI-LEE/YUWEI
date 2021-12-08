package tw.com.fcb.mimosa.workshop.vaccine.ddd.domain;

import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentProfile;

import java.util.List;

public interface AppointmentRepository {

  long save(Appointment domain);

  Appointment findById(long id);

  List<ResidentProfile> findResidents();

}
