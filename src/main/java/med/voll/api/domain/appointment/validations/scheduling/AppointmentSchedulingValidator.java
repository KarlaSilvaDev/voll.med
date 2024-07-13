package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain.appointment.AppointmentSchedulingDTO;

public interface AppointmentSchedulingValidator {
    void validate(AppointmentSchedulingDTO data);
}
