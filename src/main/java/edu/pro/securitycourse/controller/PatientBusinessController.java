package edu.pro.securitycourse.controller;
/*
  @author   george
  @project   tdd-dev
  @class  PatientController
  @version  1.0.0 
  @since 13.02.23 - 20.24
*/

import edu.pro.securitycourse.model.Patient;
import edu.pro.securitycourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v2/patients")
public class PatientBusinessController {

    private final PatientService patientService;

    @Autowired
    public PatientBusinessController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    List<Patient> getAllSortedByName() {
        return patientService.getAll()
                .stream().sorted(Comparator.comparing(Patient::getName))
                .toList();
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/hello")
    String sayHello() {
        return "Hello user or admin";
    }


}
