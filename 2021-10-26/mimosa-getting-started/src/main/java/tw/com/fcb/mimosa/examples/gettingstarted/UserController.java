package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  static List<User> usersList = new ArrayList<User>();
//  static List<UserDto> dtos = new ArrayList<UserDto>();

  @Autowired
  UserDtoMapper mapper;

  @GetMapping
  List<UserDto> getUser() {
    //		User user = new User();
    //		user.setName("matt");
    //		user.setAge(18);
    //		return user;
    //		return User.builder()
    //				.age(18)
    //				.name("matt")
    //				.build();

    List<UserDto> dtos = new ArrayList<UserDto>();
    for (User usr : usersList) {
      //			UserDto dto = UserDto.builder().name(usr.getName()).build(); //BeanUtils.copyProperties
      UserDto dto = mapper.from(usr);
      dtos.add(dto);
    }

    //		List<UserDto> java8dto = users.stream()
    //		.map(user -> UserDto.builder().name(user.getName()).build())
    //		.collect(Collectors.toList());
    return dtos;
  }

  @PostMapping
  void createUser(@RequestBody User user) {
	  usersList.add(user);
  }

  //Github 2021-10-26  -->

  //modify @PutMapping
  @PutMapping
  void modifyUser(@RequestBody User user) {
	  		int i=0;
    		for(User usr:usersList) {
    			if(usr.getName().equals(user.getName())) {
    				System.out.println("usr.getName()"+usr.getName());
    				System.out.println("user.getName()"+user.getName());
    				User modifyuser =User.builder().name(user.getName()).age(user.getAge()).build();
    				usersList.set(i, modifyuser);		
    			}
    			i++;
    		}

  }

  //delete user
  //@DeleteMapping
  @DeleteMapping
  void deleteUser(@RequestBody User user) {

		for(User usr:usersList) {
			if(usr.getName().equals(user.getName())) {
				System.out.println("delete usr.getName()"+usr.getName());
				System.out.println("delete user.getName()"+user.getName());
				usersList.remove(usr);	
				break;
			}
		}
	  
  }
}
