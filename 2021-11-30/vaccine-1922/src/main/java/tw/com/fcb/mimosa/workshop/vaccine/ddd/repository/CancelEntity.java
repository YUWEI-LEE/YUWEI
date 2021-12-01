package tw.com.fcb.mimosa.workshop.vaccine.ddd.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.Vaccine;

@Data
@Entity
@Table(name = "CANCEL")
public class CancelEntity {

  @Id
  @GeneratedValue
  Long id;
  LocalDateTime cancel_Time;
  @Enumerated(EnumType.STRING)
  Vaccine vaccine;
}
