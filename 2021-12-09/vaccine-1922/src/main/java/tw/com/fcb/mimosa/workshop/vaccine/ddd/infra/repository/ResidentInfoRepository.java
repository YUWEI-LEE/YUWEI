package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentInfoRepository extends JpaRepository<ResidentInfoEntity, Long> {

}
