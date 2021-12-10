package tw.com.fcb.mimosa.workshop.vaccine.ddd.query;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentInfo;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.AppointmentRepository;

@Service
@RequiredArgsConstructor
public class QueryResidentInfos {
  final AppointmentRepository repository;

  public List<ResidentInfo> getResidentInfo() {
    return repository.findResidentsInfo();
  }
}
