package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.doctor.DoctorDetailsDTO;
import med.voll.api.dto.doctor.DoctorListingDTO;
import med.voll.api.dto.doctor.DoctorRegistrationDTO;
import med.voll.api.dto.doctor.DoctorUpdateDTO;
import med.voll.api.model.Doctor;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorRegistrationDTO data, UriComponentsBuilder uriBuilder){
        var doctor = new Doctor(data);
        repository.save(doctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDetailsDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListingDTO>> getDoctors(@PageableDefault(size=10, sort = {"name"}) Pageable pagination){
        var page = repository.findAllByActiveTrue(pagination).map(DoctorListingDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DoctorUpdateDTO data){
        Doctor doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);
        return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        Doctor doctor = repository.getReferenceById(id);
        doctor.deactivate();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        Doctor doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
    }
}
