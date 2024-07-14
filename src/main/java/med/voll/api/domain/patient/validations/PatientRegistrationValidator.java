package med.voll.api.domain.patient.validations;

import med.voll.api.domain.patient.dto.PatientRegistrationDTO;

public interface PatientRegistrationValidator {
    void validate(PatientRegistrationDTO data);
}