package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain.DataValidationException;
import med.voll.api.domain.appointment.AppointmentSchedulingDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AppointmentAdvanceSchedulingValidator implements AppointmentSchedulingValidator {

    public void validate(AppointmentSchedulingDTO data){
        var appointmentDate = data.scheduledDate();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if (differenceInMinutes < 30){
            throw new DataValidationException("A consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }
}
