package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain.DataValidationException;
import med.voll.api.domain.appointment.dto.AppointmentSchedulingDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicOpeningHoursValidator implements AppointmentSchedulingValidator{
    public void validate(AppointmentSchedulingDTO data){
        var appointmentDate = data.scheduledDate();
        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpeningTime = appointmentDate.getHour() < 7;
        var afterClosingTime = appointmentDate.getHour() > 18;

        if (sunday || beforeOpeningTime || afterClosingTime){
            throw new DataValidationException("Consulta fora do horário de funcionamento da clínica.");
        }
    }
}
