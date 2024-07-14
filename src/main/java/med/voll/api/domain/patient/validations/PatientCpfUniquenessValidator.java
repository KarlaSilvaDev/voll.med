package med.voll.api.domain.patient.validations;

import med.voll.api.domain.DataValidationException;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.dto.PatientRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientCpfUniquenessValidator implements PatientRegistrationValidator{

    @Autowired
    private PatientRepository repository;

    @Override
    public void validate(PatientRegistrationDTO data) {
        var cpfIsRegistered = repository.existsByCpf(data.cpf());

        if (cpfIsRegistered){
            throw new DataValidationException("O cpf informado já está cadastrado no sistema.");
        }
    }
}
