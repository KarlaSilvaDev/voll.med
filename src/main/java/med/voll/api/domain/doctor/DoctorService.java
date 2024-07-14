package med.voll.api.domain.doctor;

import med.voll.api.domain.doctor.dto.DoctorDetailsDTO;
import med.voll.api.domain.doctor.dto.DoctorListingDTO;
import med.voll.api.domain.doctor.dto.DoctorRegistrationDTO;
import med.voll.api.domain.doctor.dto.DoctorUpdateDTO;
import med.voll.api.domain.doctor.validations.DoctorRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;
    @Autowired
    private List<DoctorRegistrationValidator> validators;

    public DoctorDetailsDTO register(DoctorRegistrationDTO data) {
        validators.forEach(v -> v.validate(data));

        var doctor = new Doctor(data);
        repository.save(doctor);

        return new DoctorDetailsDTO(doctor);
    }

    public Page<DoctorListingDTO> list(Pageable pagination) {
        var page = repository.findAllByActiveTrue(pagination).map(DoctorListingDTO::new);
        return page;
    }

    public DoctorDetailsDTO update(DoctorUpdateDTO data) {
        Doctor doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);
        return new DoctorDetailsDTO(doctor);
    }

    public void delete(Long id) {
        Doctor doctor = repository.getReferenceById(id);
        doctor.deactivate();
    }

    public DoctorDetailsDTO detail(Long id) {
        Doctor doctor = repository.getReferenceById(id);
        return new DoctorDetailsDTO(doctor);
    }
}
