package tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command;

import lombok.Data;

@Data
public class ReplaceResident {
  long id;
  String nhiNo;
  String phoneNo;
}
