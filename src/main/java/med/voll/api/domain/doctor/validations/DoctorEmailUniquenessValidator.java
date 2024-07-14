package med.voll.api.domain.doctor.validations;

import med.voll.api.domain.DataValidationException;
import med.voll.api.domain.doctor.dto.DoctorRegistrationDTO;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorEmailUniquenessValidator implements DoctorRegistrationValidator{
    @Autowired
    private DoctorRepository repository;

    @Override
    public void validate(DoctorRegistrationDTO data) {
        var emailIsRegistered = repository.existsByEmail(data.email());

        if (emailIsRegistered){
            throw new DataValidationException("O email informado já está cadastrado no sistema.");
        }
    }
}