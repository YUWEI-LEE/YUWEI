package tw.com.fcb.mimosa.workshop.vaccine.ddd.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentProfile;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.AppointmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryResidents {
  final AppointmentRepository repository;

  public List<ResidentProfile> getResidents() {
    return repository.findResidents();
  }
}
