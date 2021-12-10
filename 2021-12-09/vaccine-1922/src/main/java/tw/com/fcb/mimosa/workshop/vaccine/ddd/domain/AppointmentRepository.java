package tw.com.fcb.mimosa.workshop.vaccine.ddd.domain;

import java.util.List;

import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentInfo;
import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentProfile;

public interface AppointmentRepository {

  long save(Appointment domain);

  Appointment findById(long id);

  List<ResidentProfile> findResidents();

  List<ResidentInfo> findResidentsInfo();

}
