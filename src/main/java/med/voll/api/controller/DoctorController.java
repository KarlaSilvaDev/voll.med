package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DoctorListingDTO;
import med.voll.api.dto.DoctorRegistrationDTO;
import med.voll.api.dto.DoctorUpdateDTO;
import med.voll.api.model.Doctor;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DoctorRegistrationDTO data){
        repository.save(new Doctor(data));
    }

    @GetMapping
    public Page<DoctorListingDTO> getDoctors(@PageableDefault(size=10, sort = {"name"}) Pageable pagination){
        return repository.findAll(pagination).map(DoctorListingDTO::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorUpdateDTO data){
        Doctor doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }
}
