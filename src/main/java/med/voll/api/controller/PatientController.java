package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.PatientService;
import med.voll.api.domain.patient.dto.PatientListingDTO;
import med.voll.api.domain.patient.dto.PatientRegistrationDTO;
import med.voll.api.domain.patient.dto.PatientUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientRegistrationDTO data, UriComponentsBuilder uriBuilder){
        var patientDetails = service.register(data);
        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patientDetails.id()).toUri();
        return ResponseEntity.created(uri).body(patientDetails);
    }

    @GetMapping
    public ResponseEntity<Page<PatientListingDTO>> list(@PageableDefault(size=10, sort = {"name"}) Pageable pagination){
        var page = service.list(pagination);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PatientUpdateDTO data){
        var patientDetails = service.update(data);
        return ResponseEntity.ok(patientDetails);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        var patientDetails = service.detail(id);
        return ResponseEntity.ok(patientDetails);
    }
}