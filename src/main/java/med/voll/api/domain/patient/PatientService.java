package med.voll.api.domain.patient;

import med.voll.api.domain.patient.dto.PatientDetailsDTO;
import med.voll.api.domain.patient.dto.PatientListingDTO;
import med.voll.api.domain.patient.dto.PatientRegistrationDTO;
import med.voll.api.domain.patient.dto.PatientUpdateDTO;
import med.voll.api.domain.patient.validations.PatientRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    @Autowired
    private List<PatientRegistrationValidator> validators;

    public PatientDetailsDTO register(PatientRegistrationDTO data) {
        validators.forEach(v -> v.validate(data));

        var patient = new Patient(data);
        repository.save(patient);

        return new PatientDetailsDTO(patient);
    }

    public Page<PatientListingDTO> list(Pageable pagination) {
        var page = repository.findAllByActiveTrue(pagination).map(PatientListingDTO::new);
        return page;
    }

    public PatientDetailsDTO update(PatientUpdateDTO data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateData(data);
        return new PatientDetailsDTO(patient);
    }

    public void delete(Long id) {
        Patient patient = repository.getReferenceById(id);
        patient.deactivate();
    }

    public PatientDetailsDTO detail(Long id) {
        Patient patient = repository.getReferenceById(id);
        return new PatientDetailsDTO(patient);
    }
}