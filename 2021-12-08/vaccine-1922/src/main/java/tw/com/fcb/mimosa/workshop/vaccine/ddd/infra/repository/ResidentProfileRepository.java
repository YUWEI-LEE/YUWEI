package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidentProfileRepository extends JpaRepository<ResidentProfileEntity, Long> {

  Optional<ResidentProfileEntity> findByResidentId(long id);
}
