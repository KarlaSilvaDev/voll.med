package med.voll.api.domain.appointment.validations.cancellation;

import med.voll.api.domain.appointment.AppointmentCancellationDTO;

public interface AppointmentCancellationValidator {

    void validate(AppointmentCancellationDTO data);
}
