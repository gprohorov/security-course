package edu.pro.securitycourse.service;
/*
  @author   george
  @project   tdd-dev
  @class  PatientService
  @version  1.0.0 
  @since 13.02.23 - 20.42
*/

import edu.pro.securitycourse.model.Patient;
import edu.pro.securitycourse.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@PreAuthorize("hasRole('USER')")
public class PatientService {

    private final PatientRepository patientRepository;

    private List<Patient> patients = List.of(
            new Patient("1", "John", "012", "Beatles"),
            new Patient("2", "Paul", "345", "Beatles"),
            new Patient("3", "Freddie", "678", "Queen")
    );

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

  @PostConstruct
    void init(){
        patientRepository.deleteAll();
        patientRepository.saveAll(patients);
    }


    public List<Patient> getAll(){
        return patientRepository.findAll();
    }
    public List<Patient> getAllSortedByName(){
        return patientRepository.findAll()
                .stream().sorted().toList();
    }

    public Patient get(String id) {
        return patientRepository.findById(id).orElse(null);
    }
     public void delete(String id) {
         patientRepository.deleteById(id);
    }

        public Patient create(Patient patient) {
            patient.setCreatedAt(LocalDateTime.now());
           return patientRepository.save(patient);
    }

        public Patient update(Patient patient) {
            patient.setUpdatedAt(LocalDateTime.now());
           return patientRepository.save(patient);
    }


}
