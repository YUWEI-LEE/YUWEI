package tw.com.fcb.mimosa.workshop.vaccine.ddd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.ResidentMapper;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.repository.ChooseEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.repository.ResidentRepository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.web.CancelVaccine;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.web.ChooseVaccine;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.web.ReplaceResidentProfile;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.web.getResidentProfile;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.Vaccine;

@Service
@Transactional
@RequiredArgsConstructor
public class ResidentService {
  final ResidentRepository repository;
  final ResidentMapper mapper;

  public long makeAppointment(ReplaceResidentProfile command) {
    var entity = mapper.toEntity(command);
    return repository.save(entity).getId();
  }

  public void replaceResidentProfile(long id, ReplaceResidentProfile command) {
    // TODO Auto-generated method stub
    var db = repository.findById(id).orElseThrow();
    if (StringUtils.hasText(command.getPhoneNo())) {
      db.setPhoneNo(command.getPhoneNo());
      repository.save(db);
    }
  }

  public void chooseVaccine(long id, ChooseVaccine command) {
    // TODO Auto-generated method stub	    
    //	  var db = repository.findById(id).orElseThrow();
    //	    command.getVaccine().stream()
    //	    		.map(mapper :: toChooseEntity)
    //	    		.forEach(db.getChooses() :: add); 
    //	    repository.save(db);

    var db = repository.findById(id).orElseThrow();
    var append = command.getVaccine().stream()
        .map(mapper::toChooseEntity)
        //	    		.forEach(db.getChooses() :: add); 
        .collect(Collectors.toList());
    db.getChooses().addAll(append);
    repository.save(db);
  }

  public void cancelVaccine(long id, CancelVaccine command) {
    // TODO Auto-generated method stub
    //	  var db = repository.findById(id).orElseThrow();
    //	  var cancels = command.getVaccine().stream()
    //		        .map(mapper::toCancelEntity)
    //		        .collect(Collectors.toList());
    //	  repository.save(db);

    var db = repository.findById(id).orElseThrow();
    var drop = db.getChooses().stream()
        .filter(dbChoose -> command.getVaccine().contains(dbChoose.getVaccine()))
        .collect(Collectors.toList());
    db.getChooses().removeAll(drop);
    var cancels = drop.stream()
        .map(ChooseEntity::getVaccine)
        .map(mapper::toCancelEntity)
        .collect(Collectors.toList());
    db.getCancels().addAll(cancels);
    repository.save(db);
  }

  static ReplaceResidentProfile result = null;

  public List<getResidentProfile> getResidents(long id) {
    //    var db = repository.findById(id).orElseThrow();
    //    var chooseVaccineList = db.getChooses().stream()
    //    		.map(ChooseEntity::getVaccine)
    //    		.collect(Collectors.toList());
    //    System.out.println("db.getNhiNo()"+db.getNhiNo());
    //    System.out.println("db.getPhoneNo()"+db.getPhoneNo());
    //    System.out.println("chooseVaccineList"+chooseVaccineList);
    //    result.setNhiNo(db.getNhiNo());
    //    result.setPhoneNo(db.getPhoneNo());
    //    result.setChooses(chooseVaccineList);
    //    System.out.println("result+++"+result);

    return mapper.toGetRedidents(repository.findAll());
  }
}
