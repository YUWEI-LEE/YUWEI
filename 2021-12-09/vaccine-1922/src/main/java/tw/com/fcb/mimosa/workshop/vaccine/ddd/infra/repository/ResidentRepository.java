package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ResidentRepository
    extends JpaRepository<ResidentEntity, Long> {
  List<ResidentEntity> findByLastModifiedDateBetween(LocalDateTime from, LocalDateTime to);
}
