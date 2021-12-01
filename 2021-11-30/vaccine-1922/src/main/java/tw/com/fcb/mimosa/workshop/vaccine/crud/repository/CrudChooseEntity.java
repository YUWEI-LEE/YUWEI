package tw.com.fcb.mimosa.workshop.vaccine.crud.repository;

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
@Table(name = "CRUD_CHOOSE")
public class CrudChooseEntity {
  @Id
  @GeneratedValue
  Long id;
  LocalDateTime choose_Time;
  @Enumerated(EnumType.STRING)
  Vaccine vaccine;
}
