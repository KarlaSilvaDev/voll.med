package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain.DataValidationException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.dto.AppointmentSchedulingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientSingleAppointmentPerDayValidator implements AppointmentSchedulingValidator{

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentSchedulingDTO data){
        var startTime = data.scheduledDate().withHour(7);
        var endTime = data.scheduledDate().withHour(18);
        var patientHasAppointmentOnSameDay = repository.existsByPatientIdAndScheduledDateBetweenAndReasonIsNull(data.patientId(), startTime, endTime);

        if (patientHasAppointmentOnSameDay){
            throw new DataValidationException("O paciente já possui uma consulta agendada nesse dia. O limite é de uma consulta por dia.");
        }
    }
}
