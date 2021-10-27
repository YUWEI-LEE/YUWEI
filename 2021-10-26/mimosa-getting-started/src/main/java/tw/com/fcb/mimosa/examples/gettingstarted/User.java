package tw.com.fcb.mimosa.examples.gettingstarted;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

  String name;
  int age;

  //	public String getName() {
  //		return name;
  //	}
  //	public void setName(String name) {
  //		this.name = name;
  //	}
  //	public int getAge() {
  //		return age;
  //	}
  //	public void setAge(int age) {
  //		this.age = age;
  //	}

}
