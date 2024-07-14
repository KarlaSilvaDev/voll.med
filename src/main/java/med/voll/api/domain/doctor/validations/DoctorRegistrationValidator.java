package med.voll.api.domain.doctor.validations;

import med.voll.api.domain.doctor.dto.DoctorRegistrationDTO;

public interface DoctorRegistrationValidator {
    void validate(DoctorRegistrationDTO data);
}