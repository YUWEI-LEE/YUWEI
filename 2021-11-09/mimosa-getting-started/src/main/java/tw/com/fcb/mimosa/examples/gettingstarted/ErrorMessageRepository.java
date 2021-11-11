package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorMessageRepository extends JpaRepository<ErrorMessage, String> {

	Optional<ErrorMessage> findByCode(String code);
}
