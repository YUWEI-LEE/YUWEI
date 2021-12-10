package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "RESIDENT_INFO")
public class ResidentInfoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  Long residentId;
  String nhiNo;
  String phoneNo;

}
