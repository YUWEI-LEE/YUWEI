package tw.com.fcb.mimosa.examples.gettingstarted;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "errorMessage")
public class ErrorMessage {
      
	  @Id
	  @GeneratedValue
	  @Column(name = "id")
	  String id;
	  @Column(name = "category")
	  String category;
	  @Column(name = "code")
	  String code;
	  @Column(name = "translation")
	  String translation;
}
