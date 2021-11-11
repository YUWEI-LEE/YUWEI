package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.Collection;

import javax.validation.constraints.Min;

import org.checkerframework.checker.units.qual.min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.http.APIErrorException;
import tw.com.fcb.mimosa.http.APIErrorT9nException;
import tw.com.fcb.mimosa.tracing.Traced;

@Traced
@RequiredArgsConstructor
@Service
public class UserService {

  final UserRepository repository;
  final UserDtoMapper mapper;
  final ErrorMessageService errorMessageService;
  
  public User getByName(String name) {
	  return repository.findByName(name)
			  .orElseThrow(() -> {
//				 return new APIErrorException(err -> err.code("ERR1").message("name not found"));
				 return new APIErrorT9nException(err -> err.term(MyErrorCode.NAME_NOT_FOUND));
			  });
  }
  

  public Collection<User> getUsers() {
    return repository.findAll();
  }

  public User createUser(CreateUserDto user) {
    return repository.save(mapper.createUser(user));
  }

  public User replaceUser(long id, ReplaceUserDto user) {
    return repository.findById(id)
        .map(db -> { //db有值狀況    
          mapper.copyUser(user, db);
          //			db.setAge(user.getAge());
          //			db.setName(user.getName());
          return db;
        }).map(repository::save)
        .orElseThrow(() -> new IllegalArgumentException("id [" + id + "] not exist")); //db無值狀況    
  }

  public void delete(long id) {
    repository.deleteById(id);
  }

}
