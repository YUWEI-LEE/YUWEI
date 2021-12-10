package tw.com.fcb.mimosa.workshop.vaccine.ddd.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import org.springframework.data.domain.AbstractAggregateRoot;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.event.VaccineCanceled;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.Vaccine;

@Data
public class Appointment extends AbstractAggregateRoot<Appointment> {

  Long id;
  String nhiNo;
  String phoneNo;
  List<Choose> chooses;
  List<Cancel> cancels;

  public void cancelVaccine(List<Vaccine> vaccines) {
    var toDrop = this.chooses.stream()
        .filter(dbChoose -> vaccines
            .contains(dbChoose.getVaccine()))
        .collect(Collectors.toList());
    this.chooses.removeAll(toDrop);

    var cancels = toDrop.stream()
        .map(Choose::getVaccine)
        .map(this::toCancel)
        .collect(Collectors.toList());
    this.cancels.addAll(cancels);
    // 把event註冊
    this.registerEvent(new VaccineCanceled(toDrop.stream().map(Choose::getVaccine)
        .collect(Collectors.toList())));
  }

  //從CommandAssember搬過來
  private Cancel toCancel(Vaccine vaccine) {
    var domain = new Cancel();
    domain.setCancelTime(LocalDateTime.now());
    domain.setVaccine(vaccine);
    return domain;
  }

  @Override
  public Collection<Object> domainEvents() {
    return super.domainEvents();
  }

}
