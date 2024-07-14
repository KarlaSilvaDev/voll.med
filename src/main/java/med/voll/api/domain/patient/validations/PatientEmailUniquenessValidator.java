package med.voll.api.domain.patient.validations;

import med.voll.api.domain.DataValidationException;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.dto.PatientRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientEmailUniquenessValidator implements PatientRegistrationValidator{

    @Autowired
    private PatientRepository repository;

    @Override
    public void validate(PatientRegistrationDTO data) {
        var emailIsRegistered = repository.existsByEmail(data.email());

        if (emailIsRegistered){
            throw new DataValidationException("O email informado já está cadastrado no sistema.");
        }
    }
}
