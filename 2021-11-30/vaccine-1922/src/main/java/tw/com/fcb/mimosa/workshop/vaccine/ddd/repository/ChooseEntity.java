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
@Table(name = "CHOOSE")
public class ChooseEntity {
  @Id
  @GeneratedValue
  Long id;
  LocalDateTime choose_Time;
  @Enumerated(EnumType.STRING)
  Vaccine vaccine;
}
