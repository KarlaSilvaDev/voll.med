package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DoctorListingDTO;
import med.voll.api.dto.DoctorRegistrationDTO;
import med.voll.api.model.Doctor;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DoctorRegistrationDTO data){
        repository.save(new Doctor(data));
    }

    @GetMapping
    public List<DoctorListingDTO> getDoctors(){
        return repository.findAll().stream().map(DoctorListingDTO::new).toList();
    }
}
