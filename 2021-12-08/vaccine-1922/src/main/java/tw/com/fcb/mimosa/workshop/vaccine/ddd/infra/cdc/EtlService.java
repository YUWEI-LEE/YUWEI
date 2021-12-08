package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.cdc;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.Choose;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.assembler.ResidentAssembler;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ResidentEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ResidentProfileEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ResidentProfileRepository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository.ResidentRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
// etl失敗不要commit
@Transactional
@RequiredArgsConstructor
public class EtlService {

  final ResidentRepository residentRepository;
  final ResidentProfileRepository residentProfileRepository;
  final ResidentAssembler assember;

  //10秒跑一次  fixedRate 毫秒
  //  @Scheduled(fixedRate = 10_000)
  @Scheduled(fixedRate = 10 * 1000)
  public void etl() {
    // to現在時間
    var to = LocalDateTime.now();
    // form過去十秒
    var from = to.minusSeconds(10);
    // 1.找出 lastModifiedTime在from 跟to 之間的資料
    residentRepository.findByLastModifiedDateBetween(from, to)
        // 2.依序轉換成ResidentProfileEntity
        .stream()
        .map(resident -> {
          // new  profile
          return residentProfileRepository.findByResidentId(resident.getId())
              .map(profile -> assember.copyProfileEntity(resident, profile)) //copy profile
              .orElseGet(() -> assember.toProfileEntity(resident)); // new profile
        }).forEach(residentProfileRepository::save);

    // 3.save to RESIDENT_PROFILE(residentId不存在就insert,已存在就update

  }
}
