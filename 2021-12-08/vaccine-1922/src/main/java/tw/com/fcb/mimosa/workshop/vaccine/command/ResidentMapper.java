package tw.com.fcb.mimosa.workshop.vaccine.command;

import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.CancelEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ChooseEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ResidentEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.MakeAppointment;
import tw.com.fcb.mimosa.workshop.vaccine.command.web.ResidentProfile;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.Vaccine;

@Mapper
public interface ResidentMapper {

  //  CrudResidentEntity toEntity(ResidentDto dto);

  default ChooseEntity toChooseEntity(Vaccine vaccine) {
    var entity = new ChooseEntity();
    entity.setChooseTime(LocalDateTime.now());
    entity.setVaccine(vaccine);
    return entity;
  }

  default CancelEntity toCancelEntity(Vaccine vaccine) {
    var entity = new CancelEntity();
    entity.setCancelTime(LocalDateTime.now());
    entity.setVaccine(vaccine);
    return entity;
  }

  ResidentProfile fromEntity(ResidentEntity residentEntity);

  default Vaccine toChooseEntity(ChooseEntity entity) {
    return entity.getVaccine();
  }

  // @Mapping(target = "ooo", source = "xxx")
  ResidentEntity toEntity(MakeAppointment command);
}
