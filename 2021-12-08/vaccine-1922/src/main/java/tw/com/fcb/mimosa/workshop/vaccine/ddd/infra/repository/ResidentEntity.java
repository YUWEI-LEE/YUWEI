package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Table(name = "RESIDENT")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ResidentEntity {

  @Id
  @GeneratedValue
  Long id;
  String nhiNo;
  String phoneNo;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  @JoinColumn(name = "resident_id")
  List<ChooseEntity> chooses;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  @JoinColumn(name = "resident_id")
  List<CancelEntity> cancels;

  //只要有任何異動就會自動把這個欄位的資料更新
  @LastModifiedDate
  LocalDateTime lastModifiedDate;
}
