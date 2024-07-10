package med.voll.api.controller;

import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
import med.voll.api.dto.PatientDetailsDTO;
import med.voll.api.dto.PatientListingDTO;
import med.voll.api.dto.PatientRegistrationDTO;
import med.voll.api.dto.PatientUpdateDTO;
import med.voll.api.model.Patient;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientRegistrationDTO data, UriComponentsBuilder uriBuilder){
        var patient = new Patient(data);
        repository.save(patient);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDetailsDTO(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListingDTO>> list(@PageableDefault(size=10, sort = {"name"}) Pageable pagination){
        var page = repository.findAllByActiveTrue(pagination).map(PatientListingDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PatientUpdateDTO data){
        Patient patient = repository.getReferenceById(data.id());
        patient.updateData(data);
        return ResponseEntity.ok(new PatientDetailsDTO(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        Patient patient = repository.getReferenceById(id);
        patient.deactivate();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        Patient patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailsDTO(patient));
    }
}
