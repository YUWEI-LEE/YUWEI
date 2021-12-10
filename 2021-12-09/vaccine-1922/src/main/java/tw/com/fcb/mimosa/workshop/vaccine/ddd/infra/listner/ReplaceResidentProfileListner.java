package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.listner;

import java.time.LocalDateTime;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.ResidentProfileReplaced;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.EventEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.EventRepository;

@Component
@RequiredArgsConstructor
public class ReplaceResidentProfileListner {

  final EventRepository repository;

  @EventListener
  public void replaceAppointmentListner(ResidentProfileReplaced event) {
    var eventEntity = new EventEntity();
    eventEntity.setEventType(event.getClass().getSimpleName());
    eventEntity.setEventTime(LocalDateTime.now());
    eventEntity.setAggregateId(event.getId());
    eventEntity.setPhoneNo(event.getPhoneNo());
    eventEntity.setNhiNo(event.getNhiNo());
    repository.save(eventEntity);

  }
}
