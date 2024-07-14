package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.DoctorService;
import med.voll.api.domain.doctor.dto.DoctorListingDTO;
import med.voll.api.domain.doctor.dto.DoctorRegistrationDTO;
import med.voll.api.domain.doctor.dto.DoctorUpdateDTO;
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
    private DoctorService service;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorRegistrationDTO data, UriComponentsBuilder uriBuilder){
        var doctorDetails = service.register(data);
        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctorDetails.id()).toUri();
        return ResponseEntity.created(uri).body(doctorDetails);
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListingDTO>> list(@PageableDefault(size=10, sort = {"name"}) Pageable pagination){
        var page = service.list(pagination);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DoctorUpdateDTO data){
        var doctorDetails = service.update(data);
        return ResponseEntity.ok(doctorDetails);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        var doctorDetails = service.detail(id);
        return ResponseEntity.ok(doctorDetails);
    }
}