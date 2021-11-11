package tw.com.fcb.mimosa.examples.gettingstarted;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.mapstruct.Mapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateUserDto {

  @NotBlank
  String name;
  @Min(0)
  int age;
}
