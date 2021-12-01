package tw.com.fcb.mimosa.workshop.vaccine.ddd;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;

import tw.com.fcb.mimosa.workshop.vaccine.ddd.repository.CancelEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.repository.ChooseEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.repository.ResidentEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.web.ReplaceResidentProfile;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.web.getResidentProfile;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.Vaccine;

@Mapper
public interface ResidentMapper {

  List<getResidentProfile> toGetRedidents(List<ResidentEntity> entityList);

  ResidentEntity toEntity(ReplaceResidentProfile profile);

  default ChooseEntity toChooseEntity(Vaccine vaccine) {
    var entity = new ChooseEntity();
    entity.setChoose_Time(LocalDateTime.now());
    entity.setVaccine(vaccine);
    return entity;
  }

  default CancelEntity toCancelEntity(Vaccine vaccine) {
    var entity = new CancelEntity();
    entity.setCancel_Time(LocalDateTime.now());
    entity.setVaccine(vaccine);
    return entity;
  }
}
