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
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    List<Patient> getAll() {
        return patientService.getAll();
    }

    @GetMapping("/{id}")
    Patient getOne(@PathVariable String id) {
        return patientService.get(id);
    }

    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
       patientService.delete(id);
    }

    @PostMapping("/")
    Patient createOne(@RequestBody Patient patient) {
        return patientService.create(patient);
    }

    @PutMapping("/")
    Patient updateOne(@RequestBody Patient patient) {
        return patientService.update(patient);
    }

    @GetMapping("/hello")
    String sayHello() {
        return "Hello";
    }


}
